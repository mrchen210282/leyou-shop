package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wj
 * @date 2019/1/18
 */

@RequestMapping
public interface AddrressApi {

    /**
     * 校验用户名和密码是否正确的方法
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    );

    /**
     * 查询收货地址
     * @return
     */
    @GetMapping("queryAddress")
    public ResponseEntity<User> queryAddress(
            @RequestParam(value = "id", required = true) Long id
    );

}
