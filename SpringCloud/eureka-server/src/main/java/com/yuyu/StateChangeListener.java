package com.yuyu;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StateChangeListener {

    @EventListener
    public void listenerRegistered(EurekaInstanceRegisteredEvent event){
        System.out.println(event.getInstanceInfo());
    }
    @EventListener
    public void listenerCanceled(EurekaInstanceCanceledEvent event){
        System.out.println(event.getAppName());
        System.out.println(event.getServerId());

    }
}
