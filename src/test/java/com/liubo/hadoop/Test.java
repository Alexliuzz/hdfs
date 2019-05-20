package com.liubo.hadoop;

import com.liubo.hadoop.service.TestService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    TestService testService;

    @org.junit.Test
    public void test(){
        System.out.println(testService.print("liubo1111"));
    }

}
