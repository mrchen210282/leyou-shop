package com.leyou.item.mapper;

import com.leyou.cart.pojo.Sku;
import com.leyou.cart.pojo.Spu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/6/2
 */
public interface SkuMapper extends Mapper<Sku>,DeleteByIdsMapper<Sku> {

    @Select("SELECT * from tb_sku where spu_id=#{id}")
    List<Sku> skuBySpuId(@Param("id")Long id);
}
