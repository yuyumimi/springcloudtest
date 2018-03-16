package com.yuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastDFSServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FastDFSServiceApp.class,args);
    }


////    @Bean//使用@Bean注入fastJsonHttpMessageConvert
//    public HttpMessageConverters fastJsonHttpMessageConverters(){
//        //1.需要定义一个Convert转换消息的对象
//        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
//        //2.添加fastjson的配置信息，比如是否要格式化返回的json数据
////
//        FastJsonConfig fastJsonConfig=new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //3.在convert中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//
//        HttpMessageConverter<?> converter=fastConverter;
//        return new HttpMessageConverters(converter);
//    }
}
