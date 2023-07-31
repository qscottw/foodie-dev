package com.qw.controller.center;


import com.qw.controller.BasicController;
import com.qw.pojo.Users;
import com.qw.pojo.bo.center.CenterUserBO;
import com.qw.resource.FileUpload;
import com.qw.service.center.CenterUserService;
import com.qw.utils.CookieUtils;
import com.qw.utils.DateUtil;
import com.qw.utils.JsonUtils;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;
import sun.security.x509.AVA;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "user information", tags = {"user related info endpoint"})
@RequestMapping("userInfo")
public class CenterUserController extends BasicController {
    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private FileUpload fileUpload;
    @PostMapping("uploadFace")
    @ApiOperation(value = "change user avatar", notes = "change user avatar", httpMethod = "POST")
    public QWJSONResult uploadFace(
            @ApiParam(name = "userId", value = "user avatar update", required = true)
            @RequestParam String userId,
            @ApiParam(name = "file", value = "用户头像", required = true)
            MultipartFile file,
            HttpServletRequest req, HttpServletResponse res
            ){
        String fileSpace = fileUpload.getAvatarSavePath();
        String uploadPathPrefix = File.separator + userId;
        FileOutputStream fileOutputStream = null;
        String newFileName = null;
        if (file!=null){
            //获得文件名称
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)){
                //avatar-userid.png 重命名 -> ["qw", "png"]
                String fileNameArr[] = fileName.split("\\.");
                //获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];

                //文件名称重组
                //增量式额外添加增量时间
                newFileName = "avatar-"+ userId + "." + suffix;

                // uploaded
                String avatarPath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                File outFile = new File(avatarPath);

                if(outFile.getParentFile() != null){
                    outFile.getParentFile().mkdirs();
                }
                //output file to
                try {
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null){
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        } else {
            return QWJSONResult.errorMsg("文件不能为空");
        }

        String finalUserAvatarUrl = fileUpload.getAvatarServerUrl() + userId + '/' + newFileName + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
        // add user avatar to db
//        System.out.println(finalUserAvatarUrl);
        Users userResult = centerUserService.updateUserFace(userId, finalUserAvatarUrl);

        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(req, res, "user", JsonUtils.objectToJson(userResult), true);


        return QWJSONResult.ok();
    }
    @PostMapping("update")
    @ApiOperation(value = "change user info", notes = "change user info", httpMethod = "POST")
    public QWJSONResult update(
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest req, HttpServletResponse res){
        if (result.hasErrors()){
            Map<String, String> errorMap = getErrors(result);
            return QWJSONResult.errorMap(errorMap);
        }
        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(req, res, "user", JsonUtils.objectToJson(userResult), true);

        // TODO will change later, will add token, integrate redis distributed session
        return QWJSONResult.ok(userResult);
    }
    private Map<String, String> getErrors(BindingResult result){
        List<FieldError> errorList = result.getFieldErrors();
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError e : errorList){
            // field with error
            String errorField = e.getField();
            // validation error msg
            String errorMsg = e.getDefaultMessage();
            errorMap.put(errorField, errorMsg);
        }
        return errorMap;
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
