package com.leyou.user.service.controller;

import com.leyou.user.pojo.Address;
import com.leyou.user.service.service.AddressService;
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
     * 查询收货地址
     * @return
     */
    @PostMapping("queryAddresss")
    public ResponseEntity<List<Address>> queryAddresss(@RequestParam(value = "uid") Long uid) {
        List<Address> address = addressService.queryAddresss(uid);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 添加收货地址
     * @return
     */
    @PostMapping("createAddress")
    public ResponseEntity<Address> createAddress(@RequestParam(value = "uid") Long uid,
                                                 @RequestParam(value = "state") String state,
                                                 @RequestParam(value = "city") String city,
                                                 @RequestParam(value = "district") String district,
                                                 @RequestParam(value = "address") String address,
                                                 @RequestParam(value = "defaultAddress") String defaultAddress) {
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
