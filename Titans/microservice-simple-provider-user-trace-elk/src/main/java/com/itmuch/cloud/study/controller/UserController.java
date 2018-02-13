package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.study.entity.User;
import com.itmuch.cloud.study.repository.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
  private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;


  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    LOGGER.info("userRepository");

    return findOne;
  }

  public static void main(String[] args) {
    FileUtil fileUtil = new FileUtil();
    int blockFileSize = 1024 * 1024 * 500;// 15M

    // 将origin.myfile拆分
    try {
      fileUtil.splitBySize("E:\\tmp\\catalina\\catalina.out", blockFileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
