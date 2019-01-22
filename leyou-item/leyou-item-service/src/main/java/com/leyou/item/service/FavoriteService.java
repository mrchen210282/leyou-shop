package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.cart.pojo.Brand;
import com.leyou.cart.pojo.Favorite;
import com.leyou.common.PageResult;
import com.leyou.item.mapper.FavoriteMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
     * @param uid
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Favorite> queryFavorite(Long uid, int page, int rows, String sortBy, Boolean desc) {
        //开始分页
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
    }

    /***
     * 添加收藏信息
     * @param uid
     * @param itemId
     */
    public void createFavorite(Long uid,String itemId) {
        Favorite favorite = new Favorite();
        favorite.setUid(uid);
        favorite.setItemId(itemId);
        favoriteMapper.insertSelective(favorite);
    }

    /**
     * 删除收藏信息
     * @param uid
     * @param itemId
     */
    public void delFavorite(Long uid,String itemId) {
        Favorite favorite = new Favorite();
        favorite.setUid(uid);
        favorite.setItemId(itemId);
        favoriteMapper.delete(favorite);

    }
}
