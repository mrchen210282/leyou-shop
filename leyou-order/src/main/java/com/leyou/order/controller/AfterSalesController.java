package com.leyou.order.controller;


import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.pojo.AfterSales;
import com.leyou.order.pojo.OrderDetail;
import com.leyou.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class AfterSalesController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("queryAfterSales")
    public ResponseEntity<List<OrderDetail>> queryAfterSales() {
        return ResponseEntity.ok().body(orderDetailService.queryAfterSales());
    }

    @PostMapping("saveAfterSales")
    public ResponseEntity<Void> saveAfterSales(@RequestBody AfterSales afterSales) {
        orderDetailService.saveAfterSales(afterSales);
        return ResponseEntity.ok().build();
    }


}
