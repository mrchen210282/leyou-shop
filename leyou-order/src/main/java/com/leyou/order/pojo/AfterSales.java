package com.leyou.order.pojo;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_after_sales")
public class AfterSales {

    @Id
    private Long afterSalesId;

    //服务类型
    private String type;

    //订单id
    private String orderId;

    //申请原因
    private String caseContent;

    //退款金额
    private Float refundNum;

    //问题描述
    private String caseDescribe;

    //描述图片
    private String caseImg;

    @Transient
    private String[] images;

    //联系人
    private String contacts;

    //联系电话
    private String contactsPhone;

    //订单状态
    private String status;

    public Long getAfterSalesId() {
        return afterSalesId;
    }

    public void setAfterSalesId(Long afterSalesId) {
        this.afterSalesId = afterSalesId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCaseContent() {
        return caseContent;
    }

    public void setCaseContent(String caseContent) {
        this.caseContent = caseContent;
    }

    public Float getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Float refundNum) {
        this.refundNum = refundNum;
    }

    public String getCaseDescribe() {
        return caseDescribe;
    }

    public void setCaseDescribe(String caseDescribe) {
        this.caseDescribe = caseDescribe;
    }

    public String getCaseImg() {
        return caseImg;
    }

    public void setCaseImg(String caseImg) {
        this.caseImg = caseImg;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
