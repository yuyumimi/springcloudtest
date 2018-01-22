package com.yuyu.controller;

import com.yuyu.bo.UserInfo;
import com.yuyu.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoRest {

    @Autowired
    private UserRepository userRepository;
    @Value("${custom.paramter.name}")
    private String name;

    @RequestMapping(value = "/userinfo",method = RequestMethod.POST)
    public void userInfoSave(UserInfo user){
        System.out.println(user);
        this.userRepository.save(user);
    }

    @RequestMapping(value = "/userinfo",method = RequestMethod.DELETE)
    public void userInfoDelete(Long id){
        UserInfo user= this.userRepository.findUserById(id);
        if(user!=null)
        this.userRepository.delete(user);
    }
}
