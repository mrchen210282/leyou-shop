package com.leyou.item.mapper;

import com.leyou.cart.pojo.Banner;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/28
 */
public interface BannerMapper extends Mapper<Banner>,SelectByIdListMapper<Banner,Integer> {

    @Select("SELECT * from tb_circle")
    List<Banner> selectBannerList();

    @Select("SELECT * from tb_circle where id = #{id}")
    Banner selectBannerInfo(@Param("id") Integer id);

    @Insert("INSERT INTO tb_circle (name, up_url, open_url, create_date) VALUES (#{name},#{up_url},#{open_url},#{create_date})")
    void insertBanner(Banner banner);

    @Delete("DELETE from tb_circle where id = #{id}")
    void deleteBannerById(@Param("id") Integer id);
}
