package com.yuyu;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

public class TestRabbitMQ {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = "/queue")
    public void process(Message<String> message) {

        System.out.println(message);
    }
}
