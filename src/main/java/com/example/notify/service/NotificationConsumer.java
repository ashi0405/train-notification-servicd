package com.example.notify.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "ticket-status-topic", groupId = "ticket-group")
    public void consumeMessage(String message) {
        System.out.println("\u2709\uFE0F Notifying user: " + message);
        // Simulate sending email here
    }
}
