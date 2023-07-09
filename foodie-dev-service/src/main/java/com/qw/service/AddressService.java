package com.qw.service;

import com.qw.pojo.UserAddress;
import com.qw.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    /**
     * search user address by user id
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);

    /**
     * user add new address
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * user update existing address
     * @param addressBO
     */
    public void updateUserAddress(AddressBO addressBO);

    public void deleteUserAddress(String userId, String addressId);
    public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * search user addres with user id and address id
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddress(String userId, String addressId );

}
