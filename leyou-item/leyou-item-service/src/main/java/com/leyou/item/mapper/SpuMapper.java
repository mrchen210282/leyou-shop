package com.leyou.item.mapper;

import com.leyou.cart.pojo.Spu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/6/1
 */
public interface SpuMapper extends Mapper<Spu> {

    @Select("SELECT * from tb_spu where cid3=#{id}")
    List<Spu> spusByCateId(@Param("id")Long id);

}
