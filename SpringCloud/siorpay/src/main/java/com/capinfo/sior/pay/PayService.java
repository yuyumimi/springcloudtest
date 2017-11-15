package com.capinfo.sior.pay;

import com.capinfo.sior.pay.CMBC.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


@Service
public class PayService {

    @Autowired
    private RestTemplate restTemplate;

    public String pay() {
        String payinfo = JSONObject.wrap(prePay()).toString();
//        String payInfo="jsonRequestData=" + NetPay.prePay();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<String> entity = new HttpEntity<String>(payInfo, headers);
//
//        System.out.println(entity.getBody());
//        ResponseEntity<String> re= this.restTemplate.postForEntity("http://121.15.180.66:801/NetPayment/BaseHttp.dll?MB_EUserPay",entity,String.class);
//        System.out.println(re.getBody());
//        String re=RestTemplateClient.invoke("http://121.15.180.66:801/NetPayment/BaseHttp.dll?MB_EUserPay", HttpMethod.POST,payInfo,String.class);
        return payinfo;
    }
    public String pay2() {
        PayInfo payInfo=prePay();
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(payInfo.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(payInfo, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();

    }
    public static PayInfo prePay() {
        PayInfo payinfo = new PayInfo();
        payinfo.setDateTime(Util.getNowTime());
        payinfo.setBranchNo("0755");
        payinfo.setMerchantNo("000054");
        payinfo.setDate("20160624");
        payinfo.setOrderNo("9999000001");
        payinfo.setAmount("0.01");
        payinfo.setExpireTimeSpan("30");
        payinfo.setPayNoticeUrl("http://www.merchant.com/path/WAPProcResult.dll");
        payinfo.setPayNoticePara("支付");
        payinfo.setReturnUrl("");
        payinfo.setCardType("");
        payinfo.setAgrNo("9934567890987654332");// 已签约客户协议号
        //payinfo.setagrNo", "12345678901234567890");//新签约协议号
        //payinfo.setmerchantSerialNo", "20160804100538");
        payinfo.setUserID("2016062388888");
        payinfo.setMobile("18202532653");
        payinfo.setLon("30.949505");
        payinfo.setLat("50.949506");
        payinfo.setRiskLevel("3");
        payinfo.setSignNoticeUrl("");
        payinfo.setSignNoticePara("");
        payinfo.setMerchantCssUrl("");
        payinfo.setMerchantBridgeName("");

        return payinfo;
    }
}
