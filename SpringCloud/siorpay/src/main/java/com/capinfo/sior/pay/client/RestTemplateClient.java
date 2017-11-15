package com.capinfo.sior.pay.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RestTemplateClient {

    private static RestTemplate restTemplate=new RestTemplate();

    public static <T> T get(String url, Class<T> returnClass, Map<String,Object> parameters){
        if(parameters==null){
            return restTemplate.getForObject(url,returnClass);
        }
        return restTemplate.getForObject(url,returnClass,parameters);
    }

    public static <T> T invoke(String url, HttpMethod method, String body,Class<T> returnClass){
        return  invoke(url,method,null,body,returnClass,null);
    }

    public static <T> T invoke(String url, HttpMethod method,Map<String,Object> headerParams, String body,Class<T> returnClass, Map<String,Object> parameters){
        HttpHeaders headers = new HttpHeaders();
        if (headerParams != null) {
            Set<String> keys = headerParams.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                headers.add(key, headerParams.get(key).toString());
            }
        }
//        headers.add("Accept", "application/json");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        headers.setAccept(acceptableMediaTypes);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//POST数据默认编码格式
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<T> responseEntity=null;
        if(parameters==null) {
            responseEntity = restTemplate.exchange(url, method, entity, returnClass);
        }else{
             responseEntity = restTemplate.exchange(url, method, entity, returnClass,parameters);
        }
        return responseEntity.getBody();
    }
}
