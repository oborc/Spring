package com.example.demo.Controller;


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

    @GetMapping(value = "/getUserByAgeAndName",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getOneUser(@RequestParam("age") Integer age, @RequestParam("name") String name) throws InterruptedException {

        List<Object> users =  userService.selectOneUser(age,name);
        User user =(User) users.get(0);
        LOG.info("thi is user"+user.getId()+" "+user.getName());
        userService.cacheCourt(user);
        return users;
    }


    @GetMapping(value = "/getCourtById/{courtId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Court getOneCourt(@PathVariable("courtId") Long courtId) throws InterruptedException {
        return userService.selectOneCourt(courtId);
    }
}
