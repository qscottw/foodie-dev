package com.qw.controller;

import com.qw.enums.CommentLevel;
import com.qw.enums.OrderStatusEnum;
import com.qw.enums.PayMethod;
import com.qw.pojo.UserAddress;
import com.qw.pojo.bo.AddressBO;
import com.qw.pojo.bo.SubmitOrderBO;
import com.qw.pojo.vo.MerchantOrdersVO;
import com.qw.pojo.vo.OrderVO;
import com.qw.service.AddressService;
import com.qw.service.OrderService;
import com.qw.utils.CookieUtils;
import com.qw.utils.MobileEmailUtils;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@Api(value="orders api", tags = {"orders related api endpoint"})
@RequestMapping("orders")
public class OrdersController extends BasicController{
    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "user makes order", notes = "user makes order", httpMethod = "POST")
    @PostMapping("/create")
    public QWJSONResult create(@RequestBody SubmitOrderBO submitOrderBO,
                               HttpServletRequest req, HttpServletResponse res){
//        if (StringUtils.isBlank(submitOrderBO.getUserId())){
//            return QWJSONResult.errorMsg("userId doesn't exist");
//        }
        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
                && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type){
            return QWJSONResult.errorMsg("payment type not included");
        }
        // 1. create order
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(PAY_RETURN_URL);
        // 2. send this order to payment center
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "imooc");
        headers.add("password", "imooc");

        HttpEntity<MerchantOrdersVO> httpEntity = new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<QWJSONResult> responseEntity =
                restTemplate.postForEntity(paymentUrl,
                        httpEntity,
                        QWJSONResult.class);

        QWJSONResult paymentResult = responseEntity.getBody();
        // ? how to send a request in this?
        if (paymentResult.getStatus()!=200){
            return QWJSONResult.errorMsg(paymentResult.getMsg());
        }
        // TODO remove from cart the submitted items from Redis
//        CookieUtils.setCookie(req, res, FOODIE_SHOPCART, "");
        return QWJSONResult.ok(orderId);
    }

    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(@RequestParam String merchantOrderId){
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }


}
