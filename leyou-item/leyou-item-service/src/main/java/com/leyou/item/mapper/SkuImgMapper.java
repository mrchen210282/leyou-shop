package com.leyou.item.mapper;

import com.leyou.cart.pojo.SkuImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/6/2
 */
public interface SkuImgMapper extends Mapper<SkuImg>,DeleteByIdsMapper<SkuImg> {
    @Select("SELECT * from tb_sku_banner where sku_id=#{id} ORDER BY sort and img_style")
    List<SkuImg> skuImgById(@Param("id")Long id);

    @Delete("DELETE from tb_sku_banner where sku_id=#{skuId} and img_style=#{style}")
    void delImgs(@Param("skuId") Long skuId , @Param("style") Integer style);
}
