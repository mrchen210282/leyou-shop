package com.leyou.order.mapper;

import com.leyou.order.pojo.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author: wj
 * @create: 2018-05-04 10:09
 **/
@Mapper
public interface OrderStatusMapper extends tk.mybatis.mapper.common.Mapper<OrderStatus>{

    @Update("update tb_order_status set status = '3',end_time = SYSDATE(),close_time = SYSDATE() where order_id = #{orderId}")
    public void confirmOrder(@Param("orderId") Long orderId);
}
