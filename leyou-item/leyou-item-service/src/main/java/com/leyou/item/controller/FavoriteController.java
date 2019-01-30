package com.leyou.item.controller;

import com.leyou.cart.pojo.Favorite;
import com.leyou.cart.pojo.FavoriteEntity;
import com.leyou.item.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/21
 */
@RestController
@RequestMapping
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 查询收藏信息
     * @return
     */
    @PostMapping("queryFavorite")
    public ResponseEntity<List<Favorite>> queryFavorite() {
        List<Favorite> list = favoriteService.queryFavorite();

        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setTotalPage(list.size());
        favoriteEntity.setList(list);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * 添加收藏信息
     * @return
     */
    @PostMapping("createFavorite")
    public ResponseEntity<Void> createFavorite(@RequestParam(value = "itemId") Long itemId) {
        favoriteService.createFavorite(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 删除收藏信息
     * @return
     */
    @PostMapping("delFavorite")
    public ResponseEntity<Void> delFavorite(@RequestParam(value = "id") Long id) {
        favoriteService.delFavorite(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
