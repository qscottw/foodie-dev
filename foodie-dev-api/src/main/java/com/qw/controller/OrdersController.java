package com.qw.controller;

import com.qw.enums.CommentLevel;
import com.qw.enums.PayMethod;
import com.qw.pojo.UserAddress;
import com.qw.pojo.bo.AddressBO;
import com.qw.pojo.bo.SubmitOrderBO;
import com.qw.service.AddressService;
import com.qw.service.OrderService;
import com.qw.utils.MobileEmailUtils;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@Api(value="orders api", tags = {"orders related api endpoint"})
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "user makes order", notes = "user makes order", httpMethod = "POST")
    @PostMapping("/create")
    public QWJSONResult create(@RequestBody SubmitOrderBO submitOrderBO){
//        if (StringUtils.isBlank(submitOrderBO.getUserId())){
//            return QWJSONResult.errorMsg("userId doesn't exist");
//        }
        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
                && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type){
            return QWJSONResult.errorMsg("payment type not included");
        }
        // 1. create order
        orderService.createOrder(submitOrderBO);
        // TODO remove from cart the submitted items from Redis
        // 3. send to payment center the current order
        return QWJSONResult.ok();
    }


}
