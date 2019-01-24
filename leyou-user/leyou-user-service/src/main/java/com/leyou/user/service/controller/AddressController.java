package com.leyou.user.service.controller;

import com.leyou.auth.entiy.UserInfo;
import com.leyou.user.pojo.Address;
import com.leyou.user.service.interceptor.LoginInterceptor;
import com.leyou.user.service.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     *
     * @return
     */
    @GetMapping("queryAddressById/{id}")
    public ResponseEntity<Address> queryAddressById(@PathVariable("id") Long id) {

        Address address = addressService.queryAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 查询收货地址
     * @return
     */
    @PostMapping("queryAddress")
    public ResponseEntity<List<Address>> queryAddress() {
        //1.获取用户
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        Long uid = userInfo.getId();
        List<Address> address = addressService.queryAddresss(uid);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 添加收货地址
     * @return
     */
    @PostMapping("createAddress")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {

        //1.获取用户
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        Long uid = userInfo.getId();
        address.setUserId(uid);

        //省份
        if(null == address.getState()) {
            return null;
        }

        //市
        if(null == address.getCity()) {
            return null;
        }

        //区/县
        if(null == address.getDistrict()) {
            return null;
        }

        //详细地址
        if(null == address.getAddressDetail()) {
            return null;
        }

        //是否为默认地址
        addressService.createAddress(address);

        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     *设置为默认收货地址
     * @return
     */
    @PostMapping("setDefaultAddress")
    public ResponseEntity<Void> setDefaultAddress(@RequestParam(value = "id") Long id) {
        addressService.setDefaultAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     *修改收货地址
     * @return
     */
    @PostMapping("updateAddress")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        //1.获取用户
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        Long uid = userInfo.getId();
        address.setUserId(uid);
        addressService.updateAddress(address,address.getIsDefault());
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 删除收货地址
     * @return
     */
    @PostMapping("delAddress")
    public ResponseEntity<Void> delAddress(@RequestParam(value = "id") Long id) {
        addressService.delAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
