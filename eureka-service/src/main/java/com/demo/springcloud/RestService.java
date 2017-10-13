package com.demo.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
public class RestService {

  @Autowired
  private EurekaClient eurekaClient;

  @Autowired
  private DiscoveryClient discoveryClient;

  
  @Value("${server.port}")
  String port;
  
  /**
   * 测试地址
   * http://localhost:9000/hi?name=a
   * @param name
   * @return
   */
  @RequestMapping("/hi")
  public String home(@RequestParam String name) {
      return "hi "+name+",i am from port:" +port;
  }
  
  @RequestMapping(value="/test/")
  public String test(){
    return "";
  }
  
  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
    return localServiceInstance;
  }
  
  @PostMapping("/user")
  public User postUser(@RequestBody User user){
    return user;
  }
  
}
