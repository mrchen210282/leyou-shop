package com.leyou.cart.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_sku_banner")
public class SkuImg {

    @Id
    private Integer id;

    private Long skuId;

    private String img;

    private Integer sort;

    private Integer imgStyle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getImgStyle() {
        return imgStyle;
    }

    public void setImgStyle(Integer imgStyle) {
        this.imgStyle = imgStyle;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                "skuId=" + skuId +
                ", img='" + img + '\'' +
                ", order='" + sort + '\'' +
                ", imgStyle=" + imgStyle +
                '}';
    }
}