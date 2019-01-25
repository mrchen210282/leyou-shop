package com.leyou.item.mapper;

import com.leyou.cart.pojo.Favorite;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/21
 */
public interface FavoriteMapper extends Mapper<Favorite> {

    @Select("select tf.item_id as itemId,ts.images as imageURL,ts.price,ts.title from tb_favorite tf" +
            "left join tb_sku ts on tf.item_id = ts.id" +
            "where tf.uid = #{uid}" +
            "ORDER BY tf.create_time DESC")
    List<Favorite> queryFavorite(@Param("uid") Long uid);
}
