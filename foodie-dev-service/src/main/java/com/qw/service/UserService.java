package com.qw.service;

import com.qw.pojo.Users;
import com.qw.pojo.bo.UserBO;

public interface UserService {
    public boolean queryUsernameIsExist(String username);

    //只要是前端传入的，就弄成BO
    public Users createUser(UserBO userBO);


    /**
     * 检索用户名
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);

}
