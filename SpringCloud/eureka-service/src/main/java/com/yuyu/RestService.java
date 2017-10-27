package com.yuyu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import com.netflix.discovery.EurekaClient;

@RestController
public class RestService {

//  @Autowired
//  private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Value("${server.port}")
    String port;

    /**
     * 测试地址
     * http://localhost:9000/hi?name=a
     *
     * @param name
     * @return
     */
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }

    @RequestMapping(value = "/test/")
    public String test() {
        return "";
    }

    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return user;
    }

    @GetMapping(value = "/list")
    public List list() {
        List<User> list = new ArrayList();
        list.add(new User("1", "a"));
        list.add(new User("2", "b"));
        return list;
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable(value = "id") String id){
        User u=new User(id,"yuyu");
        return u;
    }
}
