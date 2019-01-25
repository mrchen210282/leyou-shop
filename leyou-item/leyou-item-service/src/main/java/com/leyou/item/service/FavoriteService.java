package com.leyou.item.service;

import com.leyou.auth.entiy.UserInfo;
import com.leyou.cart.pojo.Favorite;
import com.leyou.item.interceptor.LoginInterceptor;
import com.leyou.item.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/21
 */
@Service
public class FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    /**
     * 查询用户的全部收藏信息
     * @return
     */
    public List<Favorite> queryFavorite() {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        List<Favorite> list = favoriteMapper.queryFavorite(userInfo.getId());
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

    /***
     * 添加收藏信息
     * @param itemId
     */
    public void createFavorite(Long itemId) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        Favorite favorite = new Favorite();
        favorite.setUid(userInfo.getId());
        favorite.setItemId(itemId);
        favoriteMapper.insertSelective(favorite);
    }

    /**
     * 删除收藏信息
     */
    public void delFavorite(Long id) {
        favoriteMapper.deleteByPrimaryKey(id);
    }
}
