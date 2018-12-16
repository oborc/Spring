package com.example.demo.service;

import com.example.demo.domain.Court;
import com.example.demo.domain.User;
import com.example.demo.mapper.CourtMapper;
import com.example.demo.mapper.UserMapper;
import com.sun.org.glassfish.external.statistics.TimeStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CacheManager myCacheManager;

    @Resource
    private CourtMapper courtMapper;

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);


    @Cacheable(value = "userCache",key = "#age+':'+#name",cacheManager = "cacheManager")
    public List<Object> selectOneUser(Integer age,String name) throws InterruptedException {
        ConcurrentMapCache testCache = (ConcurrentMapCache)myCacheManager.getCache("userCache");
        ConcurrentMap<String,User> cacheMap =(ConcurrentMap) testCache.getNativeCache();
        LOG.info("selecting "+"user "+name+"from db...");
        List<Object> value = new ArrayList<>();
        User user = new User();
        user.setName(name);
        user.setAge(age);
        value.add(userMapper.selectOne(user));
        value.add(new Date());

        //court related
//        ConcurrentMapCache courtCache = (ConcurrentMapCache)myCacheManager.getCache("courtCache");
//        Court courtTemplate = new Court();
//
//        courtTemplate.setUserId(userMapper.selectOne(user).getId());
//        List<Court> courts = courtMapper.select(courtTemplate);
//        for(Court court:courts)
//        {
//            Court courtTobeCached = selectOneCourt(court.getId());
//            courtCache.put(court.getId(),courtTobeCached);
//        }

        return value;
    }

    @Async
    public void cacheCourt(User user) throws InterruptedException {
        ConcurrentMapCache courtCache = (ConcurrentMapCache)myCacheManager.getCache("courtCache");
        Court courtTemplate = new Court();

        courtTemplate.setUserId(userMapper.selectOne(user).getId());
        List<Court> courts = courtMapper.select(courtTemplate);
        for(Court court:courts)
        {
            Court courtTobeCached = selectOneCourt(court.getId());
            courtCache.put(court.getId(),courtTobeCached);
            LOG.info("in cache court async method ...");
        }

    }
    @Cacheable(value = "courtCache",key = "#courtId",cacheManager = "cacheManager")
    public Court selectOneCourt(Long courtId) throws InterruptedException {
        LOG.info("this is in the courtCache in Cache method...");
        Court courtTemplate = new Court();
        Cache cacheCourt = myCacheManager.getCache("courtCache");
        courtTemplate.setId(courtId);
        LOG.info("selecting "+"court "+courtId+"from db...");
        Long start = System.currentTimeMillis();
        Thread.sleep(10000);
        Long end = System.currentTimeMillis();
        LOG.info("thread running for "+String.valueOf(end-start)+"mill seconds");
        return courtMapper.selectOne(courtTemplate);
    }

}

