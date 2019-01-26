package com.leyou.order.pojo;

import java.util.Date;

/**
 * wj
 * 2019.1.25
 */
public class PayOrderDetail {

    private MyOrderDetail myOrderDetail;

    private String name;

    private String tel;

    private String state;

    private String city;

    private String district;

    private String addressDetail;

    private Date payTime;

    //快递公司名称
    private String expressName;

    //快递单号
    private String expressNum;

    public MyOrderDetail getMyOrderDetail() {
        return myOrderDetail;
    }

    public void setMyOrderDetail(MyOrderDetail myOrderDetail) {
        this.myOrderDetail = myOrderDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }
}
