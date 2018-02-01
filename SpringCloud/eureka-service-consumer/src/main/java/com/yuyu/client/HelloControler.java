package com.yuyu.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yuyu.bean.FeignSpringFormEncoder;
import feign.Feign;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloControler {

    @Autowired
    FeignClientInterface helloService;

    @Autowired
    UploadFeignClient uploadFeignClient;

    /**
     * 测试地址
     * http://localhost:9002/home?name=a
     * @param name
     * @return
     */
    @RequestMapping(value = "/home")
    public String hi(@RequestParam String name){
      System.out.println("feign-home");
        return helloService.home(name);
    }

    @RequestMapping(value = "/user/{id}")
    @HystrixCommand(fallbackMethod = "getUserByIdError")
    public User getUserById(@PathVariable("id") String id){
        return helloService.getUserById(id);
    }

    public User getUserByIdError(@PathVariable("id") String id){
        return new User();
    }

    @RequestMapping(value = "/file/{folder}", method = RequestMethod.POST)
    public void fileUpload(@PathVariable("folder") String folder, @RequestParam("file") MultipartFile mutiFile){
//        this.mongoService.fileUpload(folder,mutiFile);
        MongoServiceInterface fileUploadResource = Feign.builder()
                .encoder(new SpringFormEncoder()).target(MongoServiceInterface.class,"http://172.20.65.57:9100");
        fileUploadResource.fileUpload(folder,mutiFile);
    }
    @RequestMapping(value = "/upload/{folder}", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void UploadFeignClient(@PathVariable("folder") String folder, @RequestParam("file") MultipartFile mutiFile){
        this.uploadFeignClient.handleFileUpload(folder,mutiFile);
    }

    @RequestMapping(value = "/test-v1",method = RequestMethod.GET)
    public String test(HttpServletResponse response){
        return "success";
    }
    @RequestMapping(value = "/test-v2",method = RequestMethod.GET)
    public String test2(HttpServletResponse response){
        return "success2";
    }
}