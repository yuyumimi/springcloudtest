package com.yuyu;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 接收消息
     * http://localhost:10000/msg
     * @return
     */
    @RequestMapping(value = "/msg",method = RequestMethod.GET)
    public String receiveMessage(){
        Message s=this.amqpTemplate.receive("queue");

//        Object msg=this.amqpTemplate.convertSendAndReceive("queue","ok!");
        return "Receive :"+new String(s.getBody());
    }

    /**
     * 发送消息
     * http://localhost:10000/send/消息内容
     * @param msg
     */
    @RequestMapping(value = "/send/{msg}",method = RequestMethod.GET)
    public void sendMessage(@PathVariable("msg") String msg){
        this.amqpTemplate.convertAndSend("queue",msg);
    }

    /**
     * 自动接收处理报文
     * @param message
     */
    @RabbitListener(queues = "queue")
    public void process(org.springframework.messaging.Message<String> message) {
        System.out.println(message);
    }
}
