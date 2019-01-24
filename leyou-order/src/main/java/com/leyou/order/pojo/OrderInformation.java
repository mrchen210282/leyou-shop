package com.leyou.order.pojo;

public class OrderInformation {

    private Long orderId;// 订单id

    private String status; //单

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
