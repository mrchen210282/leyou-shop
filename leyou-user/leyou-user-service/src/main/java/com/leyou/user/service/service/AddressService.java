package com.leyou.user.service.service;

import com.leyou.user.pojo.Address;
import com.leyou.user.service.mapper.AddressMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        List<Address> list = addressMapper.select(address);
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
        List addressList = addressMapper.select(address);

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
    public int setDefaultAddress(Long id) {
        int isSuc = 0;
        Address addres = addressMapper.selectByPrimaryKey(id);
        if(null != addres) {
            addres.setDefaultAddress("Y");
        }
        isSuc = addressMapper.updateByPrimaryKeySelective(addres);

        //其它收货地址设置为N
        addressMapper.updateAddress(id);
        return isSuc;
    }

    /**
     * 修改收货地址
     * @param address
     * @return
     */
    public int updateAddress(Address address,String defaultAddress) {
        int isSuc = 0;
        Address addres = addressMapper.selectByPrimaryKey(address);
        if(null != addres) {
            addressMapper.updateByPrimaryKeySelective(addres);

            //查询是否有默认的收货地址
            List<Address> list = addressMapper.selectDefaultAddress(address.getUserId());
            //判断defaultAddress是否为空，不为空则把当前的设置为默认收货地址
            if(null == list || list.size() <= 0) {
                this.updateDefaultAddress(list,defaultAddress);
            }
        }
        return isSuc;
    }

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    public int delAddress(Long id) {
        int isSuc = 0;
        Address addres = addressMapper.selectByPrimaryKey(id);
        if(null != addres) {
            addressMapper.deleteByPrimaryKey(id);
            List<Address> list = addressMapper.selectAll();
            this.updateDefaultAddress(list,"");
        }
        return isSuc;
    }

    //设置默认收货地址
    private void updateDefaultAddress(List<Address> list,String defaultAddress) {
        if(null != list && list.size() > 0) {
            Address position = list.get(0);
            if(StringUtils.isNotBlank(defaultAddress)) {
                position.setDefaultAddress("Y");
            } else {
                position.setDefaultAddress("N");
            }
            addressMapper.updateByPrimaryKeySelective(position);
        }
    }
}
