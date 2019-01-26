package com.leyou.order.controller;


import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.pojo.AfterSales;
import com.leyou.order.pojo.OrderDetail;
import com.leyou.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AfterSalesController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("queryAfterSales")
    public ResponseEntity<List<OrderDetail>> queryAfterSales() {

        List<OrderDetail> list  = orderDetailService.queryAfterSales();
        list.stream().forEach(u->{
            System.out.println(u.getOrderId());
        });
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("saveAfterSales")
    public ResponseEntity<Void> saveAfterSales(@RequestBody AfterSales afterSales) {
        orderDetailService.saveAfterSales(afterSales);
        return ResponseEntity.ok().build();
    }

    @GetMapping("queryDetail/{orderId}")
    public ResponseEntity<List<OrderDetail>> queryDetail(@PathVariable String orderId) {
        return ResponseEntity.ok().body(orderDetailService.queryDetail(orderId));
    }


}
