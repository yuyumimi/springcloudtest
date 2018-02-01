package com.yuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 * http://localhost:15672访问rabbitmq管理页面
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BusServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BusServiceApp.class,args);
    }
}
