package com.qw.controller;

import com.qw.pojo.UserAddress;
import com.qw.pojo.bo.AddressBO;
import com.qw.pojo.vo.NewItemsVO;
import com.qw.service.AddressService;
import com.qw.utils.MobileEmailUtils;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(value="adress api", tags = {"address related api endpoint"})
@RequestMapping("address")
@ApiModel(value = "用户接口", description = "用户接口描述")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @ApiOperation(value = "search address with user id", notes = "search address with user id", httpMethod = "POST")
    @PostMapping("/list")
    public QWJSONResult list(@RequestParam String userId){
        if (userId==null){
            return QWJSONResult.errorMsg("userId doesn't exist");
        }
//        System.out.println(userId);
        List<UserAddress> result = addressService.queryAll(userId);
//        System.out.println(result);

        return QWJSONResult.ok(result);
    }

    @ApiOperation(value = "user add address", notes = "user add address", httpMethod = "POST")
    @PostMapping("/add")
    public QWJSONResult add(@RequestBody AddressBO addressBO){
        QWJSONResult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus()!=200){
            return checkRes;
        }
//        System.out.println(userId);
        addressService.addNewUserAddress(addressBO);
//        System.out.println(result);
        return QWJSONResult.ok();
    }

    private QWJSONResult checkAddress(AddressBO addressBO){
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return QWJSONResult.errorMsg("receiver is empty");
        }
        if (receiver.length() > 12) {
            return QWJSONResult.errorMsg("receiver name too long");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return QWJSONResult.errorMsg("phone no empty");
        }
        if (mobile.length() != 11) {
            return QWJSONResult.errorMsg("phone format incorrect");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return QWJSONResult.errorMsg("phone format incorrect");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        System.out.println(addressBO);
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return QWJSONResult.errorMsg("location cannot be empty");
        }



        return QWJSONResult.ok(addressBO);
    }

    @ApiOperation(value = "user modify address", notes = "user modify address", httpMethod = "POST")
    @PostMapping("/update")
    public QWJSONResult update(@RequestBody AddressBO addressBO){
        if (StringUtils.isBlank(addressBO.getAddressId())){
            return QWJSONResult.errorMsg("address id cannot be empty");
        }
        QWJSONResult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus()!=200){
            return checkRes;
        }
//        System.out.println(userId);
        addressService.updateUserAddress(addressBO);
//        System.out.println(result);
        return QWJSONResult.ok();
    }

    @ApiOperation(value = "user modify address", notes = "user modify address", httpMethod = "POST")
    @PostMapping("/delete")
    public QWJSONResult delete(
            @RequestParam String userId,
            @RequestParam String addressId){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return QWJSONResult.errorMsg("userId, address id cannot be empty");
        }

//       System.out.println(userId);
        addressService.deleteUserAddress(userId, addressId);
//        System.out.println(result);
        return QWJSONResult.ok();
    }

    @ApiOperation(value = "user change address to default", notes = "user change address to default", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public QWJSONResult setDefalut(
            @RequestParam String userId,
            @RequestParam String addressId){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return QWJSONResult.errorMsg("userId, address id cannot be empty");
        }

//       System.out.println(userId);
        addressService.updateUserAddressToBeDefault(userId, addressId);
//        System.out.println(result);
        return QWJSONResult.ok();
    }
}
