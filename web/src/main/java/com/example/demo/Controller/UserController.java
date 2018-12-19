package com.example.demo.Controller;


import com.example.demo.domain.Court;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Resource
    private UserService userService;

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/getUserById",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getOneUser(@RequestParam("userId") Long userId)  {

        List<Object> users =  userService.selectOneUser(userId);
        User user =(User) users.get(0);
        LOG.info("thi is user"+user.getId()+" "+user.getName());
        userService.cacheCourt(user);
        return users;
    }


    @GetMapping(value = "/getCourtById",produces = MediaType.APPLICATION_JSON_VALUE)
    public Court getOneCourt(@RequestParam("courtId") Long courtId) throws InterruptedException {
        return userService.selectOneCourt(courtId);
    }
}
