package com.example.notify.service;

import com.example.notify.emailservice.EmailService;
import com.example.notify.entity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper; // To manually deserialize the String to Ticket

    @KafkaListener(topics = "ticket-status-topic", groupId = "ticket-group")
    public void consumeMessage(String message) {
        try {
            // Deserialize the String message into a Ticket object
            Ticket ticket = objectMapper.readValue(message, Ticket.class);

            System.out.println("\u2709\uFE0F Notifying user: " + ticket.getUserName());

            // Simulate sending email here
            emailService.sendEmail(
                    "no-reply@yourapp.com",  // Replace with real user email
                    "Your Ticket Has Been Confirmed!",
                    "Dear " + ticket.getUserName() + ",\n\nYour ticket has been confirmed!\n\nTicket Details:\n" +
                            "PNR: " + ticket.getPnrNumber() + "\nTrain: " + ticket.getTrainName() + "\nStatus: " + ticket.getStatus()
            );
        } catch (Exception e) {
            // Handle any exception that occurs during deserialization
            e.printStackTrace();
        }
    }
}
