package com.leyou.item.controller;

import com.leyou.cart.pojo.Favorite;
import com.leyou.common.PageResult;
import com.leyou.item.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param uid
     * @return
     */
    @PostMapping("queryFavorite")
    public ResponseEntity<PageResult> queryFavorite(@RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "rows", defaultValue = "5") int rows,
                                                        @RequestParam("sortBy") String sortBy,
                                                        @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                        @RequestParam(value = "uid") Long uid) {
        PageResult<Favorite> pageResult = favoriteService.queryFavorite(uid, page, rows, sortBy, desc);
        if (pageResult == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pageResult);
    }

    /**
     * 添加收藏信息
     * @param uid
     * @return
     */
    @PostMapping("createFavorite")
    public ResponseEntity<Void> createFavorite(@RequestParam(value = "uid") Long uid,@RequestParam(value = "itemId") String itemId) {
        favoriteService.createFavorite(uid,itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * 删除收藏信息
     * @param uid
     * @return
     */
    @PostMapping("delFavorite")
    public ResponseEntity<Void> delFavorite(@RequestParam(value = "uid") Long uid,@RequestParam(value = "itemId") String itemId) {
        favoriteService.delFavorite(uid,itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
