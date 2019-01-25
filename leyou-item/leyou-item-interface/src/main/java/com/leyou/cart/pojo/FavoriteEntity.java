package com.leyou.cart.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wj
 * @date 2019/1/21
 */
public class FavoriteEntity {

    private int totalPage;

    private List<Favorite> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Favorite> getList() {
        return list;
    }

    public void setList(List<Favorite> list) {
        this.list = list;
    }
}
