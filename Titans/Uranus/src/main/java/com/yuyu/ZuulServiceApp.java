package com.yuyu;

import com.yuyu.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApp.class,args);
    }

//    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        //应用名为spring.application.name: consumer-hi-v1， 将映射为/v1/consumer-hi/**
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }
}
