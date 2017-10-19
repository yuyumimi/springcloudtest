package com.demo.feign.customize.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import feign.Param;
import feign.RequestLine;


@FeignClient(name = "service-hi" ,configuration=Configuration1.class)
public interface FeignClientInterface {
  
  @RequestLine("GET /hi?name={name}")
  public String home(@Param(value = "name") String name);
}
