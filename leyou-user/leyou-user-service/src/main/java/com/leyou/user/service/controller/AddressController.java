package com.leyou.user.service.controller;

import com.leyou.auth.entiy.UserInfo;
import com.leyou.interceptor.LoginInterceptor;
import com.leyou.user.pojo.Address;
import com.leyou.user.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class AddressController {

    @Autowired
    private AddressService addressService;


    /**
     * 查询收货地址
     * @return
     */
    @PostMapping("queryAddress")
    public ResponseEntity<List<Address>> queryAddress() {
        //1.获取用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        //Long uid = userInfo.getId();
        Long uid = 29l;
        List<Address> address = addressService.queryAddresss(uid);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 添加收货地址
     * @return
     */
    @PostMapping("createAddress")
    public ResponseEntity<Address> createAddress(@RequestParam(value = "state") String state,
                                                 @RequestParam(value = "city") String city,
                                                 @RequestParam(value = "district") String district,
                                                 @RequestParam(value = "address") String address,
                                                 @RequestParam(value = "defaultAddress") String defaultAddress) {

        //1.获取用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        Long uid = userInfo.getId();

        //校验数据
        Address addressObj = new Address();
        if(null == uid) {
            return null;
        } else {
            addressObj.setUserId(uid);
        }

        //省份
        if(null == state) {
            return null;
        } else {
            addressObj.setState(state);
        }

        //市
        if(null == city) {
            return null;
        } else {
            addressObj.setCity(city);
        }

        //区/县
        if(null == district) {
            return null;
        } else {
            addressObj.setDistrict(district);
        }

        //详细地址
        if(null == addressObj) {
            return null;
        } else {
            addressObj.setAddress(address);
        }

        //是否为默认地址
        if(null == defaultAddress) {
            return null;
        } else {
            addressObj.setDefaultAddress("Y");
        }

        addressService.createAddress(addressObj);

        return ResponseEntity.status(HttpStatus.OK).body(addressObj);
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
    public ResponseEntity<Address> updateAddress(@RequestParam(value = "id") Long id,
                                                 @RequestParam(value = "state") String state,
                                                 @RequestParam(value = "city") String city,
                                                 @RequestParam(value = "district") String district,
                                                 @RequestParam(value = "address") String address,
                                                 @RequestParam(value = "defaultAddress") String defaultAddress) {
        Address addressObj = new Address();
        //省份
        if(null == state) {
            return null;
        } else {
            addressObj.setState(state);
        }

        //市
        if(null == city) {
            return null;
        } else {
            addressObj.setCity(city);
        }

        //区/县
        if(null == district) {
            return null;
        } else {
            addressObj.setDistrict(district);
        }

        //详细地址
        if(null == addressObj) {
            return null;
        } else {
            addressObj.setAddress(address);
        }

        //是否为默认地址
        if(null == defaultAddress) {
            return null;
        } else {
            addressObj.setDefaultAddress("Y");
        }
        addressService.updateAddress(addressObj,defaultAddress);

        return ResponseEntity.status(HttpStatus.OK).body(addressObj);
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
