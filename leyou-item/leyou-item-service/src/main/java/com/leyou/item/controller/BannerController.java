package com.leyou.item.controller;

import com.leyou.cart.pojo.Banner;
import com.leyou.cart.pojo.ImgForm;
import com.leyou.item.service.BannerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/24
 */
@Controller
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("list")
    public ResponseEntity<List<Banner>> apiBannerList() {
       List<Banner> list = bannerService.selectBannerList();
       return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @RequestMapping("info")
    public ResponseEntity<Banner> apiBannerInfo(@RequestParam("id") String id) {
        Banner entity = bannerService.selectBannerInfo(Integer.parseInt(id));
        if(entity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @RequestMapping("add")
    public ResponseEntity apiBannerAdd(Banner entity) {
        try {
            entity.setCreateDate(new Date());
            bannerService.insertBanner(entity);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping("delete")
    public ResponseEntity apiBannerDelete(String id) {
        try {
            bannerService.deleteBannerById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping("mobileBannerSetting")
    public ResponseEntity uploadPayment(ImgForm imgForm) {
        String imgPath = "http://www.bitflash.vip/banner/";
        String path = "/home/statics/banner/";
        String imgName = RandomStringUtils.randomAlphanumeric(10)+".png";
        path = path+imgName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            String[] base64Str = imgForm.getImg().split(",");
            if (base64Str.length >= 2) {
                byte[] b = decoder.decodeBuffer(base64Str[1]);
                for (int i = 0; i < b.length; ++i) {
                    // 调整异常数据
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                // 生成jpeg图片
                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Banner appBannerEntity = new Banner();
        appBannerEntity.setId(imgForm.getId());
        appBannerEntity.setUpUrl(imgPath+imgName);
        appBannerEntity.setCreateDate(new Date());
        appBannerEntity.setOpenUrl(imgForm.getOpenAddress());
        appBannerEntity.setName(imgForm.getTitle());
        bannerService.insertBanner(appBannerEntity);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
