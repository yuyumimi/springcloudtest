package com.capinfo.sior.pay.CMBC;

import com.capinfo.sior.pay.client.RestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestCMBCInterface {

    @Autowired
    private RestTemplate restTemplate;

    public String getPubkey() {
        String json="jsonRequestData=" + NetPay.queryPubkey();
        System.out.println(json);
        String re = RestTemplateClient.invoke("http://121.15.180.72/CmbBank_B2B/UI/NetPay/DoBusiness.ashx", HttpMethod.POST, json, String.class);
        return re;
    }


}
