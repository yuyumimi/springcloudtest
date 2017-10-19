package com.demo.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
  @Value("${sior.environment}")
  private String evn;

  @Value("${abc}")
  private String abc;

  @RequestMapping("/home")
  public String home() {
    return "Hello World!";
  }

  @RequestMapping("/env")
  public String env() {
    return this.evn;
  }

  @RequestMapping("/abc")
  public String abc() {
    return this.abc;
  }
}
