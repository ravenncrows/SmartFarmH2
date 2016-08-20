package com.smartfarmh2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Asus on 18/8/2559.
 */
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/all",method = RequestMethod.GET)
    public List<User> list(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public  User getByUsername(@RequestParam("name")String name){
        return userService.findByUserName(name);
    }

    @RequestMapping(value = "/user/NewAccount",method = RequestMethod.GET)
    public List<User> newAccount(){
        return userService.findNewAccount();
    }
}
