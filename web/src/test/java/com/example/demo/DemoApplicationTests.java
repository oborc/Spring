package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)

public class DemoApplicationTests {

    @Resource
    private UserService userService;
    @Test
    public void testMapper() throws InterruptedException {
        System.out.println("creating date...");
        Date date = new Date();
        System.out.println("date:   "+date.getTime());
        System.out.println("System: "+System.currentTimeMillis());
        System.out.println("date cal:   "+date.toString());
        int n = 100000000;
        System.out.println("caculating 100000s...");
        while(n>0)
        {
            n--;
        }
        System.out.println("after caculat Sys:  "+System.currentTimeMillis());
        System.out.println("after caculat date: "+date.getTime());
        System.out.println("creating date1...");
        Date date1 = new Date();
        System.out.println("after create ...");
        System.out.println("date1:  "+date1.getTime());
        System.out.println("System: "+System.currentTimeMillis());
        System.out.println("date1 cal:  "+date.toString());
        date.after(date1);

        Date date2 = new Date();
        date2.setMinutes(date2.getMinutes()+20);
        System.out.println(date2.toString());
    }

    @Test
    public void testHashMap()
    {
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();
        for(int i=0;i<10;i++)
        {
            map.put("key"+i,i);
        }
        for (Integer value : map.values())
        {
            System.out.print(value+".");
        }
        System.out.println();
        for(String key : map.keySet())
        {
            if(map.get(key).compareTo(5) < 0)
            {
                map.remove(key);
            }
        }
        System.out.println();

        for (Integer value : map.values())
        {
            System.out.print(value+"_");
        }
    }

}
