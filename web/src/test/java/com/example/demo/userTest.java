package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.statements.SpringRepeat;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class userTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert()
    {
        for (int i = 0; i<4;i++)
        {
            User user = new User();
            user.setAge(i+10);
            user.setName("user"+i);
            userMapper.insertSelective(user);
        }
    }
}
