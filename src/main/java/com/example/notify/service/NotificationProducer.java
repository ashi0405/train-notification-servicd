package com.example.notify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNotification(String message) {
        kafkaTemplate.send("ticket-status-topic", message);
    }
}
