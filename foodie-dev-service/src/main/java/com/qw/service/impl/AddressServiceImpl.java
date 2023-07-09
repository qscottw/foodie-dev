package com.qw.service.impl;

import com.qw.enums.YesOrNo;
import com.qw.mapper.CarouselMapper;
import com.qw.mapper.UserAddressMapper;
import com.qw.pojo.Carousel;
import com.qw.pojo.UserAddress;
import com.qw.pojo.bo.AddressBO;
import com.qw.service.AddressService;
import com.qw.service.CarouselService;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress ua = new UserAddress();
        ua.setUserId(userId);

        return userAddressMapper.select(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        // 1
        Integer isDefault = 0;
        List<UserAddress> addressList = this.queryAll(addressBO.getUserId());
        if (addressList==null || addressList.isEmpty() || addressList.size()==0 ){
            isDefault=1;

        }
        String addresId = sid.nextShort();
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, newAddress);
        newAddress.setId(addresId);
        newAddress.setIsDefault(isDefault);

        newAddress.setCreatedTime(new Date());
        newAddress.setUpdatedTime(new Date());

        userAddressMapper.insert(newAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();
        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, pendingAddress);

        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());

        userAddressMapper.updateByPrimaryKeySelective(pendingAddress);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);

        userAddressMapper.delete(userAddress);
    }

    /**
     * update the user addres to be default one
     * @param userId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        //find default set as not default
        UserAddress oldDefaultAdress = new UserAddress();
        oldDefaultAdress.setUserId(userId);
        oldDefaultAdress.setIsDefault(YesOrNo.YES.type);
        List<UserAddress> list= userAddressMapper.select(oldDefaultAdress);
        //this loop just to make sure will contain only 1 element
        for (UserAddress ua : list){
            ua.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(ua);
        }
        //set to default with addressId

        UserAddress newDefaultAdress = new UserAddress();
        newDefaultAdress.setId(addressId);
        newDefaultAdress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(newDefaultAdress);

    }

    @Override
    public UserAddress queryUserAddress(String userId, String addressId) {
        UserAddress searchByUserAddress = new UserAddress();
        searchByUserAddress.setId(addressId);
        searchByUserAddress.setUserId(userId);
        
        return userAddressMapper.selectOne(searchByUserAddress);
    }
}
