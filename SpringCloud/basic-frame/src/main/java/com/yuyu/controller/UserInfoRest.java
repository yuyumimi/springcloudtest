package com.yuyu.controller;

import com.yuyu.bo.UserInfo;
import com.yuyu.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserInfoRest {

    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    public ModelAndView getUser(){
        List<UserInfo> users= this.userRepository.findAll();
        ModelAndView model=new ModelAndView();
        model.setViewName("user");
        model.getModel().put("users",users);
        return model;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView toHome(){
        List<UserInfo> users= this.userRepository.findAll();
        ModelAndView model=new ModelAndView();
        model.setViewName("index");
        model.getModel().put("users",users);
        model.getModel().put("host1","localhost");
        return model;
    }
}
