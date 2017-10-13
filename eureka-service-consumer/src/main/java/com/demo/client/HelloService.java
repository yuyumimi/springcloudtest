package com.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

  @Autowired
  RestTemplate restTemplate;
  @Autowired
  private LoadBalancerClient loadBalancerClient;

  public String hiService(String name) {
    return this.restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name,
        String.class);
  }

  public String showInfo() {
    return this.restTemplate.getForObject("http://SERVICE-HI/instance-info",String.class);
  }
  
  public String showServiceInfo(){
    ServiceInstance serviceInstance = this.loadBalancerClient.choose("SERVICE-HI");
    String info="serverInfo" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
    System.out.println(info);
    
    return info;
  }

}