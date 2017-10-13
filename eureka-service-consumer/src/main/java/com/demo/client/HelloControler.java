package com.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

  @Autowired
  HelloService helloService;

  /**
   * 测试地址 http://localhost:9002/hi?name=a
   * 
   * @param name
   * @return
   */
  @RequestMapping(value = "/hi")
  public String hi(@RequestParam String name) {
    return helloService.hiService(name);
  }

  @RequestMapping(value = "/showInfo")
  public String showInfo() {
    return helloService.showInfo();
  }
  
  @RequestMapping(value = "/showServiceInfo")
  public String showServiceInfo() {
    return helloService.showServiceInfo();
  }
}