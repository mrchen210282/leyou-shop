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
import net.sf.jsqlparser.expression.StringValue;
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
    public String createOrder(List<OrderDetail> order) {
        // 生成orderId
        String orderId = String.valueOf(idWorker.nextId());
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        Order order1 = new Order();
        // 初始化数据
        order1.setBuyerNick(user.getUsername());
        order1.setBuyerRate(false);
        order1.setCreateTime(new Date());
        order1.setOrderId(orderId);
        order1.setUserId(user.getId());
        // 保存数据
        this.orderMapper.insertSelective(order1);

        // 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCreateTime(new Date());
        orderStatus.setStatus(1);// 初始状态为未付款

        this.statusMapper.insertSelective(orderStatus);

        // 订单详情中添加orderId
        order.forEach(od -> od.setOrderId(orderId));
        // 保存订单详情,使用批量插入功能
        this.detailMapper.insertList(order);

        logger.debug("生成订单，订单编号：{}，用户id：{}", orderId, user.getId());

        return orderId;
    }

    public Order queryById(String id) {
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
    public Boolean updateStatus(String id, Integer status) {
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
}
