package com.yuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BasicFramServiceApp {

    public static void main(String[] args) {
//java -jar demo.jar --server.port=9090
        SpringApplication.run(BasicFramServiceApp.class,args);
    }
}
