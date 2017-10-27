package com.yuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApp {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        SpringApplication.run(ServiceApp.class,args);
    }
}
