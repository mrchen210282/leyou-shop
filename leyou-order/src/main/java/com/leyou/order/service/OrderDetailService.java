package com.leyou.order.service;

import com.leyou.auth.entiy.UserInfo;
import com.leyou.order.interceptor.LoginInterceptor;
import com.leyou.order.mapper.AfterSalesMapper;
import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.mapper.OrderInformationMapper;
import com.leyou.order.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderInformationMapper orderInformationMapper;

    @Autowired
    private AfterSalesMapper afterSalesMapper;

    /**
     * 查询我的中的订单信息
     *
     * @param status 1、未付款 2、已付款,未发货  3、交易成功 4、交易关闭
     * @return
     */
    public List<MyOrderDetail> queryOrder(String status) {

        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();

        List<MyOrderDetail> myOrderDetailList = new ArrayList<MyOrderDetail>();

        List<OrderInformation> list = this.orderInformationMapper.queryOrder(status, user.getId());

        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                MyOrderDetail myOrderDetail = new MyOrderDetail();
                OrderInformation orderEntity = list.get(i);
                myOrderDetail.setOrderId(orderEntity.getOrderId());

                String orderStatus = orderEntity.getStatus();
                if ("1".equals(orderStatus)) {
                    myOrderDetail.setDesc("未付款");
                    myOrderDetail.setStatus("1");
                } else if ("2".equals(orderStatus)) {
                    myOrderDetail.setDesc("待收货");
                    myOrderDetail.setStatus("2");
                } else if ("3".equals(orderStatus)) {
                    myOrderDetail.setDesc("已完成");
                    myOrderDetail.setStatus("3");
                } else {
                    myOrderDetail.setDesc("交易关闭");
                    myOrderDetail.setStatus("4");
                }

                List<OrderDetail> orderList = orderDetailMapper.queryOrderDetail(orderEntity.getOrderId());
                BigDecimal total = new BigDecimal(0);
                List<OrderMessage> orderMessageList = new ArrayList<OrderMessage>();
                for (int j = 0; j < orderList.size(); j++) {
                    OrderDetail detail = orderList.get(j);
                    OrderMessage orderMessage = new OrderMessage();
                    orderMessage.setSkuId(detail.getSkuId());
                    orderMessage.setQuantity(detail.getQuantity());
                    orderMessage.setTitle(detail.getTitle());
                    orderMessage.setPrice(detail.getPrice());
                    //orderMessage.setImageURL(detail.getImageURL());
                    orderMessage.setImageURL("https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712");

                    orderMessageList.add(orderMessage);
                    total = total.add(detail.getPrice());
                }
                myOrderDetail.setTotal(total);
                myOrderDetail.setList(orderMessageList);
                myOrderDetailList.add(myOrderDetail);
            }

        }
        return myOrderDetailList;
    }

    public List<OrderDetail> queryAfterSales() {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        Long userId = user.getId();
        Integer status = 3;
        return orderDetailMapper.queryAfterSales(status, userId);
    }

    public void saveAfterSales(AfterSales afterSales) {
        this.afterSalesMapper.insertSelective(afterSales);
    }
}
