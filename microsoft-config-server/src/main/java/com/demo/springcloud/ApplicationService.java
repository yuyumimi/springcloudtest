package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
public class ApplicationService {
  @RequestMapping("/")
  public String home() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    SpringApplication.run(ApplicationService.class, args);
  }

}