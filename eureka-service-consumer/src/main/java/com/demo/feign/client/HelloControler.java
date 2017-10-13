package com.demo.feign.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    HelloInterface helloService;
    
    /**
     * 测试地址
     * http://localhost:9002/home?name=a
     * @param name
     * @return
     */
    @RequestMapping(value = "/home")
    public String hi(@RequestParam String name){
      System.out.println("feign-home");
        return helloService.home(name);
    }

    
    @RequestMapping(value = "/testPostUser")
    public User testPostUser(User user){
        return helloService.postUser(user);
    }
}