package com.qw.service.impl.center;

import com.github.pagehelper.PageInfo;
import com.qw.mapper.UsersMapper;
import com.qw.pojo.UserAddress;
import com.qw.pojo.Users;
import com.qw.pojo.bo.center.CenterUserBO;
import com.qw.service.center.CenterUserService;
import com.qw.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class CenterUserServiceImpl implements CenterUserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryUserInfo(String userId) {

        Users user = usersMapper.selectByPrimaryKey(userId);
        user.setPassword("");
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO) {
        Users updateUser = new Users();
        BeanUtils.copyProperties(centerUserBO, updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(updateUser);

        return queryUserInfo(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserFace(String userId, String avatarUrl) {
        Users updateUser = new Users();
        updateUser.setId(userId);
        updateUser.setFace(avatarUrl);
        updateUser.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(updateUser);

        return queryUserInfo(userId);
    }

    private PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
