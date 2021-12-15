package com.example.lab2.jms;

import com.example.lab2.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;
    private final String receiverName="dataBaseWatchDoq";

    public void sendUpdateEvent(String entity, Object value){
        Event event =new Event("Update",entity,value.toString());
        jmsTemplate.convertAndSend(receiverName,event);
    }

    public void sendInsertEvent(String entity, Object value){
        Event event =new Event("Insert",entity,value.toString());
        jmsTemplate.convertAndSend(receiverName,event);
    }

    public void sendDeleteEvent(String entity, Object value){
        Event event =new Event("Delete",entity,value.toString());
        jmsTemplate.convertAndSend(receiverName,event);
    }

}
