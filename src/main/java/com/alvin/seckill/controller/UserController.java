package com.alvin.seckill.controller;


import com.alvin.seckill.mapper.UserMapper;
import com.alvin.seckill.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController{

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user")
    public String test()
    {
        User user  = userMapper.getById(13000000000l);
        System.out.println(user);
        return user.toString();
    }
}
