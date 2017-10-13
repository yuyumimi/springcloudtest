package com.demo.springcloud;

 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

 @EnableEurekaServer
 @SpringBootApplication
 public class Application {
 	
   /**
    * http://localhost:8761/
    */
   public static void main(String[] args) {
     SpringApplication.run(Application.class, args);
  }

 }