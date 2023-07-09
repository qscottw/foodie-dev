package com.qw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Api("用户api")
@ApiModel(value = "用户接口", description = "用户接口描述")
public class HelloController {
    final static Logger logger =LoggerFactory.getLogger(HelloController.class);

    @ApiOperation(value = "Say hello", notes = "say the hello worldaaaaa")
    @GetMapping("/hello")
    public Object hello(){
        logger.debug("info: hello");
        logger.info("info: hello");
        logger.warn("info: hello");
        logger.error("info: hello");

        return "Hello Worldaaaaaaaaaaaaaa";
    }


    @GetMapping("/setSession")
    public Object setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", "new user");
        session.setMaxInactiveInterval(3600);
        session.getAttribute("userInfo");
//        session.removeAttribute("userInfo");
        return "ok";
    }
}
