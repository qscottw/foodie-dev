package com.test;

import com.qw.service.StuService;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.qw.Application;
import com.qw.service.StuService;
import com.qw.service.TestTransService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransTest {

    @Autowired
    private StuService stuService;

    @Autowired
    private TestTransService testTransService;

    @Test
    public void myTest() {
//        stuService.testPropagationTrans();
        testTransService.testPropagationTrans();
    }

}
