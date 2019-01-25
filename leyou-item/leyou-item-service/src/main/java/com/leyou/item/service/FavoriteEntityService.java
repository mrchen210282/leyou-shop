package com.leyou.item.service;

import com.leyou.auth.entiy.UserInfo;
import com.leyou.cart.pojo.Favorite;
import com.leyou.cart.pojo.FavoriteEntity;
import com.leyou.item.interceptor.LoginInterceptor;
import com.leyou.item.mapper.FavoriteEntityMapper;
import com.leyou.item.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/21
 */
@Service
public class FavoriteEntityService {


    @Autowired
    private FavoriteEntityMapper favoriteEntityMapper;

    /**
     * 查询用户的全部收藏信息
     * @return
     */
    public List<FavoriteEntity> queryFavorite() {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        List<FavoriteEntity> list = favoriteEntityMapper.queryFavorite(userInfo.getId());
        return list;

        //开始分页
        /**
         PageHelper.startPage(page, rows);
         //开始过滤
         Example example = new Example(Brand.class);
         if (null != uid) {
         example.createCriteria().andEqualTo("uid",uid);

         }
         if (!StringUtils.isBlank(sortBy)) {
         String sort = sortBy + (desc ? " asc" : " desc");
         example.setOrderByClause(sort);
         }
         //分页查询
         Page<Favorite> pageinfo = (Page<Favorite>) favoriteMapper.selectByExample(example);
         return new PageResult<Favorite>(pageinfo.getTotal(), pageinfo);
         */
    }
}
