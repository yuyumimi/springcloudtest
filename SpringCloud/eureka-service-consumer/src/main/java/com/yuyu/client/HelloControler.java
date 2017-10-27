package com.yuyu.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    FeignClientInterface helloService;
    
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

    @RequestMapping(value = "/user/{id}")
    @HystrixCommand(fallbackMethod = "getUserByIdError")
    public User getUserById(@PathVariable("id") String id){
        return helloService.getUserById(id);
    }

    public User getUserByIdError(@PathVariable("id") String id){
        return new User();
    }
}