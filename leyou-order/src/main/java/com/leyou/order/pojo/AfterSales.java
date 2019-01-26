package com.leyou.order.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_after_sales")
public class AfterSales {

    @Id
    private Integer afterSalesId;

    private Integer type;

    private Integer orderId;

    private Integer caseId;

    private Float refundNum;

    private String describe;

    private String caseImg;

    private String contacts;

    private String contactsPhone;

    public Integer getAfterSalesId() {
        return afterSalesId;
    }

    public void setAfterSalesId(Integer afterSalesId) {
        this.afterSalesId = afterSalesId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Float getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Float refundNum) {
        this.refundNum = refundNum;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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
}
