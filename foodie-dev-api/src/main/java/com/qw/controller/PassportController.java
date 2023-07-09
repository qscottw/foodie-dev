package com.qw.controller;


import com.qw.pojo.Users;
import com.qw.pojo.bo.UserBO;
import com.qw.service.UserService;
import com.qw.utils.CookieUtils;
import com.qw.utils.JsonUtils;
import com.qw.utils.MD5Utils;
import com.qw.utils.QWJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;


    @GetMapping("/usernameIsExist")
    public QWJSONResult usernameIsExist(@RequestParam String username){
        // username not empty
        if(StringUtils.isBlank(username)){
            return QWJSONResult.errorMsg("用户名不为空");
        }
        // is username exist
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return QWJSONResult.errorMsg("用户名存在");
        }

        return QWJSONResult.ok();
    }

    @PostMapping("/register")
    public QWJSONResult register(@RequestBody UserBO userBo, HttpServletRequest req,
                                 HttpServletResponse res){
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();

        // both not empty
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)|| StringUtils.isBlank(confirmPwd)){
            return QWJSONResult.errorMsg("Username cannot emtpy");
        }
        // if user exist
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return QWJSONResult.errorMsg("用户名存在");
        }
        // pwd len >=6
        if (password.length()<6){
            return QWJSONResult.errorMsg("username must >=6");
        }
        // two pwd same?
        if (!password.equals(confirmPwd)){
            return QWJSONResult.errorMsg("two passwords different");
        }

        //return user
        Users userResult = userService.createUser(userBo);

        CookieUtils.setCookie(req, res, "user", JsonUtils.objectToJson(userResult), true);

        return QWJSONResult.ok(userBo);
    }

    @PostMapping("/login")
    public QWJSONResult login(@RequestBody UserBO userBo, HttpServletRequest req,
                              HttpServletResponse res) throws Exception{
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();

        // both not empty
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return QWJSONResult.errorMsg("Username cannot emtpy");
        }
        // if user exist


        // login
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (userResult == null){
            return QWJSONResult.errorMsg("Username/password incorrect");
        }


        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(req, res, "user", JsonUtils.objectToJson(userResult), true);
        //TODO generate user toke, store in redis session
        //TODO sync shopping cart data across computer
        return QWJSONResult.ok(userResult);
    }

    @PostMapping("/logout")
    public QWJSONResult logout(@RequestParam String userId,
                               HttpServletRequest req,
                               HttpServletResponse res){
        //清除用户相关的cookie
        CookieUtils.deleteCookie(req, res, "user");
        // TODO 清空购物车
        // TODO 分布式会话中需要清除用户数据

        return QWJSONResult.ok();

    }


    private Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
