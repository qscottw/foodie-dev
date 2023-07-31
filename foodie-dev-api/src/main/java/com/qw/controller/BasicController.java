package com.qw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@RestController
public class BasicController {
   public static final Integer COMMENT_PAGE_SIZE = 10;
   public static final Integer ORDER_PAGE_SIZE = 5;
   public static final Integer PAGE_SIZE = 20;
   public static final String FOODIE_SHOPCART = "shopcart";
   public static final String PAY_RETURN_URL = "http://localhost:8088/orders/notifyMerchantOrderPaid";
   String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

   //用户头像存储地址
   public static final String AVATAR_SAVE_PATH = "C:" + File.separator + "Users" + File.separator + "19753" +
        File.separator + "Desktop" + File.separator + "java_projects" +
        File.separator + "imgs" + File.separator + "foodie";
}
