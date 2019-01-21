package com.leyou.order.mapper;

import com.leyou.order.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;

import java.util.List;

/**
 * @author: wj
 * @create: 2019.1.18
 **/
@Mapper
public interface OrderQueryMapper extends tk.mybatis.mapper.common.Mapper<OrderDetail>, SelectByIdListMapper<OrderDetail,Long> {

    /**
     * 查询订单信息
     * @param uid
     * @return
     */
    @Select("SELECT tod.title,tod.num,tod.price,tod.image FROM tb_order tt " +
            "LEFT JOIN tb_order_detail tod ON tt.order_id = tod.order_id " +
            "LEFT JOIN tb_order_status tos ON tod.order_id = tod.order_id" +
            "WHERE tos.status = #{status} AND tt.user_id = #{uid}")
    List<OrderDetail> queryOrderDetail(@Param("uid") Long uid, @Param("status") String status);
}
