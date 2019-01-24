package com.leyou.item.controller;

import com.leyou.bo.GoodsBo;
import com.leyou.cart.pojo.SkuImg;
import com.leyou.cart.pojo.Spu;
import com.leyou.item.service.GoodsService;
import com.leyou.cart.pojo.Sku;
import com.leyou.cart.pojo.SpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Qin PengCheng
 * @date 2018/6/2
 */

@RestController
@RequestMapping("goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addGoods(@RequestBody GoodsBo goods){
        this.goodsService.addGoods(goods);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 根据id查询商品细节的方法
     * @param id
     * @return
     */
    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpuDetailById(@PathVariable("id")Long id){
        SpuDetail spuDetail = goodsService.querySpuDetailById(id);
        if (spuDetail==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(spuDetail);
    }

    /**
     * 删除商品的方法
     * @param id
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<Void> deleteSpuById(@RequestParam("id") Long id){
                this.goodsService.deleteSpuById(id);
                return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 更改商品的上下架状态
     * @param id
     * @param selable
     * @return
     */
    @PutMapping("spu")
    public ResponseEntity<Void> updateSealStand(@RequestParam("id")Long id,@RequestParam("selable")Boolean selable){
        this.goodsService.updateSealStand(id,selable);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    /**
     * 修改商品的方法
     * @param goods
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateGoods(@RequestBody GoodsBo goods){
        this.goodsService.updateGoods(goods);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    /**
     * 查询sku的方法
     * @param id
     * @return
     */
    @GetMapping("/sku/list")
    public ResponseEntity<List<Sku>> querySkuList(@RequestParam("id")Long id){
     List<Sku> skus =   this.goodsService.querySkuList(id);
     return ResponseEntity.status(HttpStatus.OK).body(skus);
    }

    /***
     * 查询商品详情
     * @param id
     * @return
     */
    @GetMapping("/sku/info")
    public ResponseEntity<Map<String,Object>> querySkuById(@RequestParam("id") Long id){
        Sku sku = this.goodsService.querySkuBySkuId(id);
        //获取图片
        List<SkuImg> imgs = this.goodsService.skuImgs(id);
        if (sku == null || imgs == null || imgs.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("sku",sku);
        map.put("imgs",imgs);
        return ResponseEntity.ok(map);
    }

    /**
     * 根据分类查询商品列表
     */
    @GetMapping("/carts/list")
    public ResponseEntity<List<Sku>> cartsList(@RequestParam("cateId")Long id){
        List<Spu> spus =   this.goodsService.spusByCateId(id);
        List<Sku> skus = new LinkedList<Sku>();
        for (Spu su : spus){
            List<Sku> sku = goodsService.querySkusBySpuId(su.getId());
            skus.add(sku.get(0));
        }
        return ResponseEntity.status(HttpStatus.OK).body(skus);
    }

    /**
     * 根据spuid查询商品列表
     */
    @GetMapping("/spus/list")
    public ResponseEntity<List<Sku>> spusList(@RequestParam("spuid")Long spuid){
        List<Sku> skus =   this.goodsService.skuBySpuId(spuid);
        return ResponseEntity.status(HttpStatus.OK).body(skus);
    }

}
