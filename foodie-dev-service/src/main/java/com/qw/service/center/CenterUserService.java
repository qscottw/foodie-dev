package com.qw.service.center;

import com.qw.pojo.UserAddress;
import com.qw.pojo.Users;
import com.qw.pojo.bo.center.CenterUserBO;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;


public interface CenterUserService {
    public Users queryUserInfo(String userId);

    /**
     * change center user information
     * @param userId
     * @param centerUserBO
     * @return
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    public Users updateUserFace(String userId, String avatarUrl);
}
