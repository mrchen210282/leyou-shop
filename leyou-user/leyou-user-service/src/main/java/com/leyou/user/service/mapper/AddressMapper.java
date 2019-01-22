package com.leyou.user.service.mapper;

import com.leyou.user.pojo.Address;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/18
 */
public interface AddressMapper extends Mapper<Address> {

    @Update("UPDATE tb_address SET default_address = 'N' WHERE id = #{id}" )
    void updateAddress(@Param("id") Long id);

    //查询是否有默认的收货地址
    @Select("SELECT * FROM tb_address WHERE uid = #{uid} AND default_address = 'Y'")
    List<Address> selectDefaultAddress(@Param("uid") Long uid);
}
