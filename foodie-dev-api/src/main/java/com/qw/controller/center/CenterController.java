package com.qw.controller.center;


import com.qw.pojo.Users;
import com.qw.service.center.CenterUserService;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "center -- user center", tags = {"desplay user info endpoint"})
@RequestMapping("center")
@RestController
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;


    @GetMapping("userInfo")
    public QWJSONResult userInfo(@ApiParam(name = "userId", value = "user id", required = true)
                                 @RequestParam String userId){
        Users user=centerUserService.queryUserInfo(userId);
        return QWJSONResult.ok(user);
    }



}
