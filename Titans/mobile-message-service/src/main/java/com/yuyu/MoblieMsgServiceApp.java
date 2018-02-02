package com.yuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MoblieMsgServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(MoblieMsgServiceApp.class,args);
    }
}
