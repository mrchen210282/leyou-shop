package com.leyou.user.service.controller;

import com.leyou.user.pojo.OwnBackground;
import com.leyou.user.service.service.OwnBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class OwnBackgroundController {

    @Autowired
    private OwnBackgroundService ownBackgroundService;

    /**
     * 查询我的背景图
     * @return
     */
    @PostMapping("queryBackground")
    public ResponseEntity<List<OwnBackground>> queryBackground() {
        List<OwnBackground> list = ownBackgroundService.queryOwnBackground();
        OwnBackground ownBackground = new OwnBackground();
        ownBackground.setId(Long.valueOf("333"));
        ownBackground.setImg1("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=0&spn=0&di=7742675710&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=967395617%2C3601302195&os=1463459597%2C1718984994&simid=0%2C0&adpicid=0&lpn=0&ln=1667&fr=&fmq=1548124239037_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fwww.pptbz.com%2Fpptpic%2FUploadFiles_6909%2F201211%2F2012111719294197.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3F2657rf_z%26e3Bptwgyw_z%26e3BvgAzdH3Fr5fp-m90mm-b1nmkdnuamuu90kuknjjml0u8lv9nwul-8_z%26e3Bfip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
        ownBackground.setImg2("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=2&spn=0&di=151264369080&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=49764040%2C3750999451&os=1530077966%2C3237075674&simid=101575951%2C669020311&adpicid=0&lpn=0&ln=1667&fr=&fmq=1548124239037_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic33.photophoto.cn%2F20141022%2F0019032438899352_b.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bri5p5ri5p5_z%26e3BvgAzdH3FrtvAzdH3F8cl8ndl9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
        List list1 = new ArrayList();
        list1.add(ownBackground);
        return ResponseEntity.status(HttpStatus.OK).body(list1);
    }
}
