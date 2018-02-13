package com.yuyu.controller;

import com.yuyu.bo.OpenLock;
import com.yuyu.service.SendMobileMessageService;
import org.apache.axis.client.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

@RestController
@RefreshScope
public class SendMobileMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMobileMessageService.class);

    @Autowired
    private SendMobileMessageService sendMobileMessageService;

    @Value("${neo.hello}")
    private String param;
    @RequestMapping(value = "/config" ,method = RequestMethod.POST)
    public String testConfig() {
        return param;
    }

    @RequestMapping(value = "/sendMsg" ,method = RequestMethod.POST)
    public String sendMsg(String tel, String message, String type) {
        OpenLock ol = this.sendMobileMessageService.queryOpenLockByCode(type);
        String xmlString = null;
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        final String qnamePrefixForMid="http://impl.service.worker.smsys.capinfo.com/";
        final String methodNameForMid="sendMessage";
        try {
            Call call = (Call) service.createCall();
            call.setTimeout(10000);
            call.setTargetEndpointAddress(ol.getTelurl());
            call.setOperationName(new QName(qnamePrefixForMid, methodNameForMid));//wsdl里面描述的接口名称

            call.addParameter(new QName(qnamePrefixForMid, "appId"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter(new QName(qnamePrefixForMid, "pwd"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter(new QName(qnamePrefixForMid, "telNumber"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter(new QName(qnamePrefixForMid, "content"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter(new QName(qnamePrefixForMid, "messageNum"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter(new QName(qnamePrefixForMid, "type"), org.apache.axis.encoding.XMLType.XSD_INT,javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter(new QName(qnamePrefixForMid, "remark"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数

            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型
            List<Object> parameters = new ArrayList<Object>();
            parameters.add(ol.getTelappid());
            parameters.add(ol.getTelpwd());
            parameters.add(tel);
            parameters.add(message);
            parameters.add(ol.getTelmessagenum());
            parameters.add(ol.getType());
            parameters.add(ol.getRemark());

            xmlString = (String)call.invoke(parameters.toArray());
        } catch (Exception e) {
            xmlString = "手机验证码 ==>> 通过网络服务, 获取数据出现异常!!!";
            e.printStackTrace();
        }
        LOGGER.info("手机平台返回:"+xmlString);
        return xmlString;
    }
}
