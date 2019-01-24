package com.leyou.order.controller;

import com.leyou.order.pojo.MyOrderDetail;
import com.leyou.order.service.OrderDetailService;
import com.leyou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 分页查询我的订单
     *
     * @param status 订单状态
     * @return 分页订单数据
     */
    @PostMapping("queryOrder")
    //@ApiOperation(value = "分页查询当前用户订单，并且可以根据订单状态过滤", notes = "分页查询当前用户订单")
    public ResponseEntity<List<MyOrderDetail>> queryOrder(
            @RequestParam(value = "status") String status) {
        List<MyOrderDetail> list = orderDetailService.queryOrder(status);
        return ResponseEntity.ok(list);
    }
}
