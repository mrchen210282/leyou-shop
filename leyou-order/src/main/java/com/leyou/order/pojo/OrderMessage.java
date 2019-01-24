package com.leyou.order.pojo;

import java.math.BigDecimal;

public class OrderMessage {

    private Integer num;// 商品购买数量

    private String title;// 商品标题

    private BigDecimal price;// 商品单价

    private String image;// 图片

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
