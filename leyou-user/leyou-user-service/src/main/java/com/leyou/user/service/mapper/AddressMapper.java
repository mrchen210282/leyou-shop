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

    //    @Update("UPDATE tb_address SET is_default = 'false' WHERE id = #{userId}" )
//    void updateAddress(@Param("userId") Long userId);

    //查询是否有默认的收货地址
    @Select("SELECT * FROM tb_address WHERE user_id = #{userId} AND is_default = 'true'")
    List<Address> selectDefaultAddress(@Param("userId") Long userId);

    //更新默认的收货地址
    @Update("UPDATE tb_address SET is_default = 'false' WHERE user_id = #{userId}")
    void updateDefaultAddress(@Param("userId") Long userId);
}
