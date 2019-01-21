package com.leyou.user.service.service;

import com.leyou.user.pojo.Address;
import com.leyou.user.pojo.User;
import com.leyou.user.service.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/18
 */
@Service
public class AddressService {


    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据uid查询收货地址
     * @param uid
     * @return
     */
    public List<Address> queryAddresss(Long uid) {
        if(null == uid) {
            return null;
        }
        Address address = new Address();
        address.setUserId(uid);
        List<Address> list = addressMapper.selectByExample(address);
        return list;
    }

    /**
     * 添加收货地址 如果是第一次添加则为默认地址
     *
     * @param address
     * @return
     */
    public int createAddress(Address address) {
        int isSuc = 0;
        List addressList = addressMapper.selectByExample(address);
        if (null == addressList || addressList.size() <= 0) {
            address.setDefaultAddress("Y");
        } else {
            address.setDefaultAddress("N");
        }
        isSuc = addressMapper.insertSelective(address);
        return isSuc;
    }

    /**
     * 设置为默认收货地址
     * @return
     */
    public int setDefaultAddress(Address address) {
        int isSuc = 0;
        Address addres = addressMapper.selectByPrimaryKey(address);
        if(null != addres) {
            addres.setDefaultAddress("Y");
        }
        isSuc = addressMapper.updateByPrimaryKeySelective(address);
        return isSuc;
    }

    /**
     * 修改收货地址
     * @param address
     * @return
     */
    public int updateAddress(Address address) {
        int isSuc = 0;
        Address addres = addressMapper.selectByPrimaryKey(address);
        if(null != addres) {
            addressMapper.updateByPrimaryKeySelective(addres);
        }
        return isSuc;
    }

    /**
     * 删除收货地址
     * @param address
     * @return
     */
    public int delAddress(Address address) {
        int isSuc = 0;
        Address addres = addressMapper.selectByPrimaryKey(address);
        if(null != addres) {
            addressMapper.deleteByPrimaryKey(addres);
        }
        return isSuc;
    }
}
