package com.leyou.goodsDetail.client;

import com.leyou.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Qin PengCheng
 * @date 2018/6/8
 */
@FeignClient("item-service")
public interface SpecClient extends SpecApi{
}
