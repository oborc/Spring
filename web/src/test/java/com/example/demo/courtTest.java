package com.example.demo;


import com.example.demo.domain.Court;
import com.example.demo.mapper.CourtMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class courtTest {

    @Resource
    private CourtMapper courtMapper;


    @Test
    public void testInsert()
    {
        for(int i =0;i<4;i++)
        {
            Court court  = new Court();
            court.setName("court"+i);
            court.setUserId(3L);
            courtMapper.insertSelective(court);
        }
    }
}
