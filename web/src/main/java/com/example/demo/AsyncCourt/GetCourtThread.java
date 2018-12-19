package com.example.demo.AsyncCourt;

import com.example.demo.domain.Court;
import com.example.demo.domain.User;
import com.example.demo.mapper.CourtMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import javax.annotation.Resource;
import java.util.List;

public class GetCourtThread implements Runnable {


    private CacheManager myCacheManager;

    private UserMapper userMapper;


    private CourtMapper courtMapper;

    public CacheManager getMyCacheManager() {
        return myCacheManager;
    }

    public void setMyCacheManager(CacheManager myCacheManager) {
        this.myCacheManager = myCacheManager;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CourtMapper getCourtMapper() {
        return courtMapper;
    }

    public void setCourtMapper(CourtMapper courtMapper) {
        this.courtMapper = courtMapper;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    private final Logger LOG = LoggerFactory.getLogger(GetCourtThread.class);

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        cacheCourt(this.getUser());
    }

    private void cacheCourt(User user) {
        ConcurrentMapCache courtCache = (ConcurrentMapCache) myCacheManager.getCache("courtCache");
        Court courtTemplate = new Court();

        courtTemplate.setUserId(userMapper.selectOne(user).getId());
        List<Court> courts = courtMapper.select(courtTemplate);
        for (Court court : courts) {
            if (courtCache.get(court.getId(), Court.class) != null) {
                continue;
            }
            Court courtTobeCached = userService.selectOneCourt(court.getId());
            courtCache.put(court.getId(), courtTobeCached);
            LOG.info("in thread  method ...");
        }

    }
}
