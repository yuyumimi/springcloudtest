package com.yuyu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
  @Value("${profile:default}")
  private String profile;

  @GetMapping("/profile")
  public String hello() {
    return this.profile;
  }
}
