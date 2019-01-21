package com.leyou.user.service.controller;

import com.leyou.common.PageResult;
import com.leyou.user.pojo.Address;
import com.leyou.user.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询收货地址
     * @return
     */
    @GetMapping("queryAddresss")
    public ResponseEntity<List<Address>> queryAddresss(@RequestParam(value = "uid") Long uid) {
        List<Address> address = addressService.queryAddresss(uid);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 添加收货地址
     * @return
     */
    @GetMapping("createAddress")
    public ResponseEntity<Address> createAddress(@RequestParam(value = "uid") Long uid,
                                                 @RequestParam(value = "state") String state,
                                                 @RequestParam(value = "city") String city,
                                                 @RequestParam(value = "district") String district,
                                                 @RequestParam(value = "address") String addrress,
                                                 @RequestParam(value = "defaultAddress") String defaultAddress) {
        //校验数据
        Address address = new Address();
        if(null == uid) {
            return null;
        } else {
            address.setUserId(uid);
        }

        //省份
        if(null == state) {
            return null;
        } else {
            address.setState(state);
        }

        //市
        if(null == city) {
            return null;
        } else {
            address.setCity(city);
        }

        //区/县
        if(null == district) {
            return null;
        } else {
            address.setDistrict(district);
        }

        //详细地址
        if(null == address) {
            return null;
        } else {
            address.setAddress(addrress);
        }

        //是否为默认地址
        if(null == defaultAddress) {
            return null;
        } else {
            address.setDefaultAddress("Y");
        }

        addressService.createAddress(address);

        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     *设置为默认收货地址
     * @return
     */
    @GetMapping("setDefaultAddress")
    public ResponseEntity<Address> setDefaultAddress(@RequestParam(value = "uid") Long uid) {

        Address address = new Address();

        address.setDefaultAddress("Y");
        addressService.setDefaultAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     *修改收货地址
     * @return
     */
    @GetMapping("updateAddress")
    public ResponseEntity<Address> updateAddress() {
        Address address = new Address();
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * 删除收货地址
     * @return
     */
    @GetMapping("delAddress")
    public ResponseEntity<Address> delAddress(@RequestParam(value = "uid") Long uid) {
        Address address = new Address();


        return ResponseEntity.status(HttpStatus.OK).body(address);
    }
}
