package com.leyou.order.service;

import com.leyou.auth.entiy.UserInfo;
import com.leyou.order.interceptor.LoginInterceptor;
import com.leyou.order.mapper.AfterSalesMapper;
import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.mapper.OrderInformationMapper;
import com.leyou.order.mapper.*;
import com.leyou.order.pojo.*;
import com.leyou.utils.IdWorker;
import com.leyou.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import tk.mybatis.mapper.entity.Example;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * wj
 */
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderInformationMapper orderInformationMapper;

    @Autowired
    private AfterSalesMapper afterSalesMapper;
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询我的中的订单信息
     *
     * @param status 1、未付款 2、已付款,未发货  3、交易成功 4、交易关闭
     * @return
     */
    public List<MyOrderDetail> queryOrder(String status) {

        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();

        List<MyOrderDetail> myOrderDetailList = new ArrayList<MyOrderDetail>();

        List<OrderInformation> list = this.orderInformationMapper.queryOrder(status, user.getId(), null);

        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                MyOrderDetail myOrderDetail = new MyOrderDetail();
                OrderInformation orderEntity = list.get(i);
                myOrderDetail.setOrderId(orderEntity.getOrderId());

                String orderStatus = orderEntity.getStatus();
                if ("1".equals(orderStatus)) {
                    myOrderDetail.setDesc("未付款");
                    myOrderDetail.setStatus("1");
                } else if ("2".equals(orderStatus)) {
                    myOrderDetail.setDesc("待收货");
                    myOrderDetail.setStatus("2");
                } else {
                    myOrderDetail.setDesc("已完成");
                    myOrderDetail.setStatus("3");
                }

                List<OrderDetail> orderList = orderDetailMapper.queryOrderDetail(orderEntity.getOrderId());
                BigDecimal total = new BigDecimal(0);
                List<OrderMessage> orderMessageList = new ArrayList<OrderMessage>();
                for (int j = 0; j < orderList.size(); j++) {
                    OrderDetail detail = orderList.get(j);
                    OrderMessage orderMessage = new OrderMessage();
                    orderMessage.setSkuId(detail.getSkuId());
                    orderMessage.setQuantity(detail.getQuantity());
                    orderMessage.setTitle(detail.getTitle());
                    orderMessage.setPrice(detail.getPrice());
                    //orderMessage.setImageURL(detail.getImageURL());
                    orderMessage.setImageURL("https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712");

                    orderMessageList.add(orderMessage);
                    total = total.add(detail.getPrice());
                }
                myOrderDetail.setTotal(total);
                myOrderDetail.setList(orderMessageList);
                myOrderDetailList.add(myOrderDetail);
            }
        }
        return myOrderDetailList;
    }

    public List<OrderDetail> queryAfterSales() {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        Long userId = user.getId();
        Integer status = 3;
        return orderDetailMapper.queryAfterSales(status, userId);
    }

    public void saveAfterSales(AfterSales afterSales) {
        if(StringUtils.isNotEmpty(afterSales.getCaseImg())){
            //正式库图片地址
            //String path = "/home/statics/qrcode/";
            //测速地址
            String path = "D://";
            String imgName = MD5Util.getMD5Format( afterSales.getOrderId().toString()+ System.currentTimeMillis())+afterSales.getOrderId().toString()+".png";
            path = path + imgName;
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                // Base64解码
                String[] base64Str = afterSales.getCaseImg().split(",");
                if (base64Str.length >= 2) {
                    byte[] b = decoder.decodeBuffer(base64Str[1]);
                    for (int i = 0; i < b.length; ++i) {
                        if (b[i] < 0) {// 调整异常数据
                            b[i] += 256;
                        }
                    }
                    // 生成jpeg图片
                    OutputStream out = new FileOutputStream(path);
                    out.write(b);
                    out.flush();
                    out.close();
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            afterSales.setCaseImg(path);
        }
        afterSales.setAfterSalesId(idWorker.nextId());

        this.afterSalesMapper.insertSelective(afterSales);
    }


    public void confirmOrder(Long orderId) {
        orderStatusMapper.confirmOrder(orderId);
    }

    /**
     * 添加收货地址
     * @param addressId
     */
    public void addOrderDetail(Long addressId,Long orderId) {

        ReceiveAddress receiveAddress = new ReceiveAddress();

        //1.获取用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        receiveAddress.setAddressId(addressId);
        receiveAddress.setUserId(userInfo.getId());
        receiveAddress.setOrderId(orderId);
        receiveAddressMapper.insertSelective(receiveAddress);
    }

    public PayOrderDetail queryAddressDetail(Long orderId, String status) {
        PayOrderDetail payOrderDetail = new PayOrderDetail();
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        List<MyOrderDetail> myOrderList = this.queryOrder(status,user.getId(),orderId);
        if(null != myOrderList && myOrderList.size() > 0) {
            MyOrderDetail myOrderDetail = myOrderList.get(0);
            payOrderDetail.setMyOrderDetail(myOrderDetail);
        }
        Example example = new Example(ReceiveAddress.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        List<ReceiveAddress> receiveAddressList = receiveAddressMapper.selectByExample(example);
        if(null != receiveAddressList && receiveAddressList.size() > 0) {
            ReceiveAddress receiveAddress = receiveAddressList.get(0);
            Address address = addressMapper.selectByPrimaryKey(receiveAddress.getAddressId());
            payOrderDetail.setName(address.getName());
            payOrderDetail.setTel(address.getTel());
            payOrderDetail.setState(address.getState());
            payOrderDetail.setCity(address.getCity());
            payOrderDetail.setDistrict(address.getDistrict());
            payOrderDetail.setAddressDetail(address.getAddressDetail());
            payOrderDetail.setPayTime(receiveAddress.getPayTime());
            payOrderDetail.setExpressName("顺风");
            payOrderDetail.setExpressNum("333333");

        }
        return payOrderDetail;
    }

    private List<MyOrderDetail> queryOrder(String status, Long userId, Long orderId) {
        List<MyOrderDetail> myOrderDetailList = new ArrayList<MyOrderDetail>();
        List<OrderInformation> list = this.orderInformationMapper.queryOrder(status, userId, orderId);
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                MyOrderDetail myOrderDetail = new MyOrderDetail();
                OrderInformation orderEntity = list.get(i);
                myOrderDetail.setOrderId(orderEntity.getOrderId());

                String orderStatus = orderEntity.getStatus();
                if ("1".equals(orderStatus)) {
                    myOrderDetail.setDesc("未付款");
                    myOrderDetail.setStatus("1");
                } else if ("2".equals(orderStatus)) {
                    myOrderDetail.setDesc("待收货");
                    myOrderDetail.setStatus("2");
                } else {
                    myOrderDetail.setDesc("已完成");
                    myOrderDetail.setStatus("3");
                }

                List<OrderDetail> orderList = orderDetailMapper.queryOrderDetail(orderEntity.getOrderId());
                BigDecimal total = new BigDecimal(0);
                List<OrderMessage> orderMessageList = new ArrayList<OrderMessage>();
                for (int j = 0; j < orderList.size(); j++) {
                    OrderDetail detail = orderList.get(j);
                    OrderMessage orderMessage = new OrderMessage();
                    orderMessage.setSkuId(detail.getSkuId());
                    orderMessage.setQuantity(detail.getQuantity());
                    orderMessage.setTitle(detail.getTitle());
                    orderMessage.setPrice(detail.getPrice());
                    //orderMessage.setImageURL(detail.getImageURL());
                    orderMessage.setImageURL("https://pop.nosdn.127.net/19e33c9b-6c22-4a4b-96da-1cb7afb32712");

                    orderMessageList.add(orderMessage);
                    total = total.add(detail.getPrice());
                }
                myOrderDetail.setTotal(total);
                myOrderDetail.setList(orderMessageList);
                myOrderDetailList.add(myOrderDetail);
            }
        }
        return myOrderDetailList;
    }

    public List<OrderDetail> queryDetail(String orderId){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        return orderDetailMapper.select(orderDetail);
    }

}
