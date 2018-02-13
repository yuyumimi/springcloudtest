package com.test;

import com.yuyu.controller.CookieController;
import com.yuyu.WebFrontEndServiceApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 引入Spring对JUnit4的支持
@RunWith(SpringJUnit4ClassRunner.class)
// 指定Spring Boot的启动类，或@SpringBootTest(classes=Bootstrap.class)
@SpringBootTest(classes=WebFrontEndServiceApp.class)
// 开启Web应用的配置，用于模拟ServletContext
@WebAppConfiguration
public class AppTest {
    // 用于模拟调用Controller的接口发起请求
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private RedisTemplate redisTemplate;
    @Before
    public void setUp() throws Exception{
        // 初始化加权限
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    /**
     * 单独测试controller
     * @throws Exception
     */
    public void setControllerUp() throws Exception{
        // 初始化对CookieController的模拟
        mvc = MockMvcBuilders.standaloneSetup(new CookieController()).build();
    }
    @Test
    @WithMockUser(username = "test1",password = "123")
    public void test(){

        try {
            mvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(content().string(equalTo("index")));


        } catch (Exception e) {
            e.printStackTrace();
        }
//        redisTemplate.opsForValue().set("student:1", "kirito"); // <2>
//        System.out.println(redisTemplate.opsForValue().get("student:1"));

    }

    @Test
    public void testSession(){
        try {
            mvc.perform(MockMvcRequestBuilders.get("/test/cookie").param("browser","chrom").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("index"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
