package com.microservice.skeleton.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthCenterApplication.class, args);
	}
}
