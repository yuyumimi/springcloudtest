package com.yuyu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

  @GetMapping("/{id}")
  public String findById(@PathVariable String id) {
    return id;
  }
}
