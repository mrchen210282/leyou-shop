package com.leyou.item.service;

import com.leyou.cart.pojo.Banner;
import com.leyou.cart.pojo.Brand;
import com.leyou.item.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/28
 */
@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 查询轮播图列表
     */
    public List<Banner> selectBannerList(){
        return this.bannerMapper.selectBannerList();
    }

    /**
     * 查询轮播图详情
     */
    public Banner selectBannerInfo(Integer id){
        return this.bannerMapper.selectBannerInfo(id);
    }

    /**
     * 添加轮播图详情
     */
    public void insertBanner(Banner banner){
        this.bannerMapper.insertBanner(banner);
    }

    /**
     * 删除轮播图详情
     */
    public void deleteBannerById(Integer id){
        this.bannerMapper.deleteBannerById(id);
    }

}
