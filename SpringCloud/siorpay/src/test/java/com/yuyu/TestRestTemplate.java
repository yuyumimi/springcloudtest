package com.yuyu;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.capinfo.sior.pay.PayInfo;
import com.capinfo.sior.pay.XMLUtil;
import com.yuyu.CW.DATASET;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class TestRestTemplate {

  private static RestTemplate restTemplate;

  static {
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    requestFactory.setReadTimeout(5000);
    requestFactory.setConnectTimeout(5000);
    //
    // // 添加转换器
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    messageConverters
        .add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    messageConverters.add(new FormHttpMessageConverter());
    messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
    messageConverters.add(new MappingJackson2HttpMessageConverter());// json字符串转换为相应实体

    restTemplate = new RestTemplate();
    restTemplate.setMessageConverters(messageConverters);
    restTemplate.setRequestFactory(requestFactory);
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

  }

  public static void test() {
    PayInfo s = restTemplate.getForObject("http://172.20.65.57:8080/testPay",
        PayInfo.class);
    System.out.println(s);
  }

  public static void testxml() {
    PayInfo s = restTemplate
        .getForObject("http://172.20.65.57:8080/testPay_xml", PayInfo.class);
    System.out.println(s);
  }

  public static void testcwxml()
      throws JsonParseException, JsonMappingException, IOException, JAXBException {
     ResponseEntity<DATASET> s = restTemplate.getForEntity(
     "http://172.20.65.57:8080/testcw_xml", DATASET.class);

//    ResponseEntity<String> results = restTemplate.exchange(
//        "http://172.20.65.57:8080/testcw_xml", HttpMethod.GET, null,
//        String.class);
//
//    System.out.println(results.getBody());
//    Object s= xmlToBean(results.getBody(), DATASET.class);
    System.out.println(s);
  }
  public static void testcwxmlToObject() throws JAXBException, IOException {
    ResponseEntity<String> results = restTemplate.exchange(
        "http://172.20.65.57:8080/testcw_xml", HttpMethod.GET, null,
        String.class);

    System.out.println(results.getBody());
    DATASET s= XMLUtil.xmlToBean(results.getBody(), DATASET.class);
            System.out.println(s);
  }


  public static void main(String[] args) {
    System.out.println(new Date());
    try {
      testcwxmlToObject();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(new Date());
    }
  }

  static String get() {
    return "<DATASET>" + "<DATA>" + "  <D>" + "      <JE>1</JE>"
        + "      <ND>1</ND>" + "      <PSNSN>1</PSNSN>"
        + "      <STATUS>1</STATUS>" + "  </D>" + "  <D>" + "      <JE>2</JE>"
        + "      <ND>2</ND>" + "      <PSNSN>2</PSNSN>"
        + "      <STATUS>2</STATUS>" + "  </D>" + "</DATA>" + "<HEADINFO>"
        + "    <CALLSTATUS>1成功 0失败</CALLSTATUS>" + "    <ERRMSG>0</ERRMSG>"
        + "</HEADINFO>" + "</DATASET>";
  }
}
