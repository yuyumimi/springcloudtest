package com.demo.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *  Feign是一个声明式Web Service客户端。使用Feign能让编写Web Service客户端更加简单,
 *  它的使用方法是定义一个接口，然后在上面添加注解，同时也支持JAX-RS标准的注解。Feign也支持可拔插式的编码器和解码器。SpringCloud对Feign进行了封装，使其支持了Spring
 *  MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
 * @author yuyu
 *
 */
@FeignClient(name = "service-hi")
public interface HelloInterface {
  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  public String home(@RequestParam(value = "name") String name);
  
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public User postUser(@RequestBody User user);
}