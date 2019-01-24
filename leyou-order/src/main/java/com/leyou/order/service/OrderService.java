package com.leyou.order.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.auth.entiy.UserInfo;
import com.leyou.common.PageResult;
import com.leyou.order.interceptor.LoginInterceptor;
import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.mapper.OrderInformationMapper;
import com.leyou.order.mapper.OrderMapper;
import com.leyou.order.mapper.OrderStatusMapper;
import com.leyou.order.pojo.*;
import com.leyou.utils.IdWorker;
import com.leyou.utils.PayHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: HuYi.Zhang
 * @create: 2018-05-04 10:11
 **/
@Service
public class OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper detailMapper;

    @Autowired
    private OrderStatusMapper statusMapper;

    @Autowired
    private OrderInformationMapper orderInformationMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public Long createOrder(Order order) {
        // 生成orderId
        long orderId = idWorker.nextId();
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        // 初始化数据
        order.setBuyerNick(user.getUsername());
        order.setBuyerRate(false);
        order.setCreateTime(new Date());
        order.setOrderId(orderId);
        order.setUserId(user.getId());
        // 保存数据
        this.orderMapper.insertSelective(order);

        // 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCreateTime(order.getCreateTime());
        orderStatus.setStatus(1);// 初始状态为未付款

        this.statusMapper.insertSelective(orderStatus);

        // 订单详情中添加orderId
        order.getOrderDetails().forEach(od -> od.setOrderId(orderId));
        // 保存订单详情,使用批量插入功能
        this.detailMapper.insertList(order.getOrderDetails());

        logger.debug("生成订单，订单编号：{}，用户id：{}", orderId, user.getId());

        return orderId;
    }

    public Order queryById(Long id) {
        // 查询订单
        Order order = this.orderMapper.selectByPrimaryKey(id);

        // 查询订单详情
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(id);
        List<OrderDetail> details = this.detailMapper.select(detail);
        order.setOrderDetails(details);

        // 查询订单状态
        OrderStatus status = this.statusMapper.selectByPrimaryKey(order.getOrderId());
        order.setStatus(status.getStatus());
        return order;
    }

    public PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status) {
        try {
            // 分页
            PageHelper.startPage(page, rows);
            // 获取登录用户
            UserInfo user = LoginInterceptor.getLoginUser();
            // 创建查询条件
            Page<Order> pageInfo = (Page<Order>) this.orderMapper.queryOrderList(user.getId(), status);

            return new PageResult<>(pageInfo.getTotal(), pageInfo);
        } catch (Exception e) {
            logger.error("查询订单出错", e);
            return null;
        }
    }

    @Transactional
    public Boolean updateStatus(Long id, Integer status) {
        OrderStatus record = new OrderStatus();
        record.setOrderId(id);
        record.setStatus(status);
        // 根据状态判断要修改的时间
        switch (status) {
            case 2:
                record.setPaymentTime(new Date());// 付款
                break;
            case 3:
                record.setConsignTime(new Date());// 发货
                break;
            case 4:
                record.setEndTime(new Date());// 确认收获，订单结束
                break;
            case 5:
                record.setCloseTime(new Date());// 交易失败，订单关闭
                break;
            case 6:
                record.setCommentTime(new Date());// 评价时间
                break;
            default:
                return null;
        }
        int count = this.statusMapper.updateByPrimaryKeySelective(record);
        return count == 1;
    }


    /**
     * 查询我的中的订单信息
     *
     * @param status 1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭 6、已评价
     * @return
     */
    public List<MyOrderDetail> queryOrder(String status) {
        List<MyOrderDetail> myOrderList = new ArrayList<MyOrderDetail>();
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();

        List<MyOrderDetail> myOrderDetailList = new ArrayList<MyOrderDetail>();

        List<OrderInformation> list = this.orderInformationMapper.queryOrder(status, user.getId());
        if (null != list && list.size() > 0) {
            for(int i = 0; i < list.size(); i++) {
                OrderInformation orderDetail = list.get(i);
                for(int j = 0; j < list.size(); j++) {
                    OrderInformation order = list.get(j);
                    if(orderDetail.getOrderId().equals(order.getOrderId())) {
                        BigDecimal bigDecimal = new BigDecimal(0);
                        OrderInformation orderDetail1 = list.get(j);
                        MyOrderDetail myOrderDetail = new MyOrderDetail();

                        myOrderDetail.setOrderId(orderDetail1.getOrderId());
                        myOrderDetail.setSkuId(orderDetail1.getSkuId());
                        String orderStatus = orderDetail1.getStatus();
                        if("1".equals(orderStatus)) {
                            myOrderDetail.setDesc("未付款");
                            myOrderDetail.setStatus("1");
                        } else if ("2".equals(orderStatus)) {
                            myOrderDetail.setDesc("待收货");
                            myOrderDetail.setStatus("2");
                        } else if ("3".equals(orderStatus)) {
                            myOrderDetail.setDesc("待收货");
                            myOrderDetail.setStatus("3");
                        } else if ("4".equals(orderStatus)) {
                            myOrderDetail.setDesc("已完成");
                            myOrderDetail.setStatus("4");
                        } else if ("5".equals(orderStatus)) {
                            myOrderDetail.setDesc("交易关闭");
                            myOrderDetail.setStatus("5");
                        } else {
                            myOrderDetail.setDesc("已完成");
                            myOrderDetail.setStatus("6");
                        }
                        //算
                        bigDecimal.add(orderDetail1.getPrice());

                        List<OrderMessage> orderMessagesList = new ArrayList<OrderMessage>();

                        OrderMessage orderMessag = new OrderMessage();
                        orderMessag.setImage(orderDetail1.getImageURL());
                        orderMessag.setNum(orderDetail1.getQuantity());
                        orderMessag.setTitle(orderDetail1.getTitle());
                        orderMessag.setPrice(orderDetail1.getPrice());
                        orderMessagesList.add(orderMessag);

                        myOrderDetail.setList(orderMessagesList);

                        myOrderDetailList.add(myOrderDetail);

                    }
                }
            }
        }
        return myOrderDetailList;

    }

}
