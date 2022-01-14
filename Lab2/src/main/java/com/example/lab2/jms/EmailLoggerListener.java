package com.example.lab2.jms;

import com.example.lab2.models.Email;
import com.example.lab2.models.Event;
import com.example.lab2.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailLoggerListener implements EventListener{
    @Autowired
    private EmailService emailService;

    @Override
    @JmsListener(destination = "dataBaseWatchDoq")
    public void update(Event event) {
        String msg = String.format("%s happend.", event.getAction());
        Email email = new Email(msg, "admin@mail.ru");
        emailService.save(email);
    }
}
