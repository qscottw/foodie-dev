package com.qw.service.impl;

import com.qw.enums.Sex;
import com.qw.mapper.StuMapper;
import com.qw.mapper.UsersMapper;
import com.qw.pojo.Users;
import com.qw.pojo.bo.UserBO;
import com.qw.service.UserService;
import com.qw.utils.DateUtil;
import com.qw.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper usersMapper;


    @Autowired
    private Sid sid;


    public static final String USER_FACE = "https://www.pngarts.com/files/5/User-Avatar-PNG-Free-Download.png";
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        return result == null ? false : true;

    }

    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        String userID = sid.nextShort();

        Users user = new Users();
        user.setId(userID);
        user.setUsername(userBO.getUsername());
        //要进行加密

        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认昵称是用户名
        user.setNickname(userBO.getUsername());
        user.setFace(USER_FACE);

        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);
        return user;
    }
}
