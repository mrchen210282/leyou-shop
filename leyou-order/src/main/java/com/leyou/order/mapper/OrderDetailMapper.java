package com.leyou.order.mapper;

import com.leyou.order.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: HuYi.Zhang
 * @create: 2018-05-04 10:09
 **/
@Mapper
public interface OrderDetailMapper extends tk.mybatis.mapper.common.Mapper<OrderDetail>, InsertListMapper<OrderDetail> {

    @Select("select * from tb_order_detail where order_id = #{orderId}")
    List<OrderDetail> queryOrderDetail(@Param("orderId") Long orderId);

    List<OrderDetail> queryAfterSales(@Param("status")Integer status, @Param("userId")Long userId);

}
