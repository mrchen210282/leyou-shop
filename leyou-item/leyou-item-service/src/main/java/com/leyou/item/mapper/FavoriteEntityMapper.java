package com.leyou.item.mapper;

import com.leyou.cart.pojo.FavoriteEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/21
 */
public interface FavoriteEntityMapper extends Mapper<FavoriteEntity> {

    List<FavoriteEntity> queryFavorite(@Param("uid") Long uid);
}
