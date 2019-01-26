package com.leyou.order.pojo;

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
}
