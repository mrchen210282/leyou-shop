package com.leyou.order.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * wj
 */
@Table(name = "tb_receive_address")
public class ReceiveAddress {

    @Id
    private Long id;// id

    private Long addressId;

    private Long userId;

    private Long orderId;

    //下单时间
    private Date payTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
