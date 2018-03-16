import com.yuyu.FastDFSServiceApp;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;

// 引入Spring对JUnit4的支持
@RunWith(SpringJUnit4ClassRunner.class)
// 指定Spring Boot的启动类，或@SpringBootTest(classes=Bootstrap.class)
//@SpringBootTest(classes=FastDFSServiceApp.class)
// 开启Web应用的配置，用于模拟ServletContext
@SpringBootTest(classes=FastDFSServiceApp.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@WebAppConfiguration
@DirtiesContext

public class AppTest {
    // 用于模拟调用Controller的接口发起请求
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHelloWorld() {
        ResponseEntity<byte[]> entity = this.restTemplate.getForEntity("/download", byte[].class,"group1","M00/00/00/wKiZZlqgixeAW1sqAABmK_fVInI977.jpg","test_"+ RandomStringUtils.random(1000));
        File file=new File("e://tmp//");
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(entity.getBody());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void test(){

        try {
            MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders.get("/download").accept(MediaType.APPLICATION_JSON)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
