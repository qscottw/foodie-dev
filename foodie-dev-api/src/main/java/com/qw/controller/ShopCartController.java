package com.qw.controller;

import com.qw.pojo.bo.ShopCartBO;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "card endpoint api", tags = {"cart endpoint related api"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {
    @ApiOperation(value = "add item to cart", notes = "add item to cart", httpMethod = "POST")
    @PostMapping("/add")
    public QWJSONResult add(
            @ApiParam(name= "userId", value = "user's id", required = true)
            @RequestParam String userId,
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest req,
            HttpServletResponse res){
        if (StringUtils.isBlank(userId)){
            return QWJSONResult.errorMsg("usrid cannot be empty");
        }
        // TODO front logged in, and add to cart backend will sync shopping cart stored in redis
        System.out.println(shopCartBO);
        return QWJSONResult.ok();
    }

    @ApiOperation(value = "delete item from cart", notes = "delete item from cart", httpMethod = "POST")
    @PostMapping("/del")
    public QWJSONResult del(
            @ApiParam(name= "userId", value = "user's id", required = true)
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest req,
            HttpServletResponse res){
        if (StringUtils.isBlank(userId)||StringUtils.isBlank(itemSpecId)){
            return QWJSONResult.errorMsg("usrid itemSpecId cannot be empty");
        }

        // TODO front logged in, and delete to cart backend will sync shopping cart stored in redis
        System.out.println(itemSpecId);
        return QWJSONResult.ok();
    }
}
