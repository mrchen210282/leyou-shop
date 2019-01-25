package com.leyou.order.pojo;

import java.math.BigDecimal;
import java.util.List;

public class MyOrderDetail {

    private Long orderId;// 订单id

    private String status;

    private String desc;

    private BigDecimal total; //商品总价

    //运费
    private BigDecimal carriage;

    //实付总价
    private BigDecimal payTotal;

    private List<OrderMessage> list;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderMessage> getList() {
        return list;
    }

    public void setList(List<OrderMessage> list) {
        this.list = list;
    }

    public BigDecimal getCarriage() {
        return carriage;
    }

    public void setCarriage(BigDecimal carriage) {
        this.carriage = carriage;
    }

    public BigDecimal getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(BigDecimal payTotal) {
        this.payTotal = payTotal;
    }
}
