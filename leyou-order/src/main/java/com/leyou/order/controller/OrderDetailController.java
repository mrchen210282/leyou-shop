package com.leyou.order.controller;

import com.leyou.order.pojo.MyOrderDetail;
import com.leyou.order.pojo.PayOrderDetail;
import com.leyou.order.pojo.ReceiveAddress;
import com.leyou.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 *
 */
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
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * 确认订单
     * @param orderId
     * @return
     */
    @PostMapping("confirmOrder")
    public ResponseEntity<Void> confirmOrder(@RequestParam(value = "orderId") Long orderId) {
        orderDetailService.confirmOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 添加收货地址
     * @param addressId
     * @return
     */
    @PostMapping("addOrderDetail")
    public ResponseEntity<Void> addOrderDetail(@RequestParam(value = "addressId") Long addressId,@RequestParam(value = "orderId") Long orderId) {
        orderDetailService.addOrderDetail(addressId,orderId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("queryAddressDetail")
    public ResponseEntity<PayOrderDetail> queryAddressDetail(@RequestParam(value = "orderId") Long orderId,@RequestParam(value = "status") String status) {
        PayOrderDetail payOrderDetail = orderDetailService.queryAddressDetail(orderId,status);
        return ResponseEntity.status(HttpStatus.OK).body(payOrderDetail);
    }
}
