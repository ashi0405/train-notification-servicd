package com.example.notify.service;

import com.example.notify.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private KafkaTemplate<String, Ticket> kafkaTemplate;

    public void sendNotification(Ticket ticket) {
        kafkaTemplate.send("ticket-status-topic", ticket);
    }
}
