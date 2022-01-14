package com.example.lab2.jms;

import com.example.lab2.models.Event;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Component
public class EventManager {

    JmsTemplate jmsTemplate;
    Topic topic;

    public EventManager(JmsTemplate jmsTemplate) throws JMSException {
        this.jmsTemplate = jmsTemplate;
        this.topic = jmsTemplate.getConnectionFactory().createConnection().createSession().createTopic("dataBaseWatchDoq");
    }

    public void sendUpdateEvent(String entity, Object value){
        Event event =new Event("Update",entity,value.toString());
        updateListeners(event);
    }

    public void sendInsertEvent(String entity, Object value){
        Event event =new Event("Insert",entity,value.toString());
        updateListeners(event);
    }

    public void sendDeleteEvent(String entity, Object value){
        Event event =new Event("Delete",entity,value.toString());
        updateListeners(event);
    }
    public void updateListeners(Event event) {
        jmsTemplate.convertAndSend(topic, event);
    }

}
