package com.leyou.order.mapper;

import com.leyou.order.pojo.AfterSales;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

@Mapper
public interface AfterSalesMapper extends tk.mybatis.mapper.common.Mapper<AfterSales>, InsertListMapper<AfterSales> {


}
