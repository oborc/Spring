package com.example.demo.Controller;


import com.example.demo.AsyncCourt.GetCourtThread;
import com.example.demo.domain.Court;
import com.example.demo.domain.User;
import com.example.demo.mapper.CourtMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CacheManager myCacheManager;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CourtMapper courtMapper;

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/getUserById",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getOneUser(@RequestParam("userId") Long userId)  {

        List<Object> users =  userService.selectOneUser(userId);
        User user =(User) users.get(0);
        LOG.info("thi is user"+user.getId()+" "+user.getName());
        myCacheManager.getCache("courtCache");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,1000,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        GetCourtThread thread = new GetCourtThread();
        thread.setUser(user);
        thread.setCourtMapper(courtMapper);
        thread.setMyCacheManager(myCacheManager);
        thread.setUserMapper(userMapper);
        thread.setUserService(userService);
        threadPoolExecutor.execute(thread);
        return users;
    }


    @GetMapping(value = "/getCourtById",produces = MediaType.APPLICATION_JSON_VALUE)
    public Court getOneCourt(@RequestParam("courtId") Long courtId) throws InterruptedException {
        return userService.selectOneCourt(courtId);
    }
}
