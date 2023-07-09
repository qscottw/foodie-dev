package com.qw.service.impl;

import com.qw.mapper.StuMapper;
import com.qw.pojo.Stu;
import com.qw.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {

//    @Autowired
    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
//        return null;

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveStu() {
        Stu stu = new Stu();
        stu.setName("java");
        stu.setAge(100);
        stuMapper.insert(stu);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setName("macy");
        stu.setAge(20);
        stuMapper.updateByPrimaryKey(stu);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);
    }
//    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveChildren() {
        saveChild1();
        saveChild2();
    }

    public void saveChild1() {
        Stu stu1 = new Stu();
        stu1.setName("child-1");
        stu1.setAge(11);
        stuMapper.insert(stu1);
    }
    public void saveChild2() {
        Stu stu2 = new Stu();
        stu2.setName("child-2");
        stu2.setAge(22);
        stuMapper.insert(stu2);
    }
}