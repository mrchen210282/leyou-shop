package com.leyou.cart.controller;

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.management.modelmbean.ModelMBean;
import java.util.List;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2018-10-25 20:41
 * @Feature:
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @PostMapping("addCart")
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        this.cartService.addCart(cart);
        return ResponseEntity.ok().build();
    }

    /**
     * 查询购物车
     * @return
     */
    @PostMapping("queryCart")
    public ResponseEntity<List<Cart>> queryCartList(){
        List<Cart> carts = this.cartService.queryCartList();
        if(carts == null){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(carts);
    }

    /**
     * 修改购物车中商品数量
     * @return
     */
    @PutMapping("updateCart")
    public ResponseEntity<Map> updateNum(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num){
        if(num<1){
            return ResponseEntity.ok(new ModelMap("code","最低值为1"));
        }
        this.cartService.updateNum(skuId,num);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除购物车中的商品
     * @param skuId
     * @return
     */
    @DeleteMapping("deleteCart/{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId") String skuId){
        this.cartService.deleteCart(skuId);
        return ResponseEntity.ok().build();
    }

}
