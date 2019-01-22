package com.leyou.user.service.service;

import com.leyou.user.pojo.Address;
import com.leyou.user.pojo.OwnBackground;
import com.leyou.user.service.mapper.AddressMapper;
import com.leyou.user.service.mapper.OwnBackgroundMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wj
 * @date 2019/1/22
 */
@Service
public class OwnBackgroundService {


    @Autowired
    private OwnBackgroundMapper ownBackgroundMapper;

    /**
     * 查询我的背景图
     * @return
     */
    public List<OwnBackground> queryOwnBackground() {
        List<OwnBackground> list = ownBackgroundMapper.selectAll();
        return list;
    }


}
