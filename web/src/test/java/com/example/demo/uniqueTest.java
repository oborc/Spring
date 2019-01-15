package com.example.demo;


import com.example.demo.domain.originUser;
import com.example.demo.mapper.originUserMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLDataException;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class uniqueTest {

    @Resource
    private originUserMapper originUserMapper;

    @Test
    public void unqiue()
    {
        originUserMapper.insertUnique("ji","ruitong");
        originUserMapper.insertUnique("ji","ruitong");

    }
}
