package com.leyou.order.mapper;

import com.leyou.order.pojo.OrderInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: wj
 * @create: 2019.01.23
 **/
@Mapper
public interface OrderInformationMapper extends tk.mybatis.mapper.common.Mapper<OrderInformation> {

    List<OrderInformation> queryOrder(@Param("status") String status,@Param("id") Long id,@Param("orderId") Long orderId);
}
