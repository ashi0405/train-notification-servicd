package com.example.notify.scheduler;

import com.example.notify.entity.Ticket;
import com.example.notify.repository.TicketRepository;
import com.example.notify.service.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusScheduler {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotificationProducer producer;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 12) // Every 12 hours
    public void checkStatusAndNotify() {
        System.out.println("Scheduled job started - checking status...");

        List<Ticket> tickets = ticketRepository.findAll();
        for (Ticket ticket : tickets) {
            // Simulate status check from API
            String updatedStatus = simulateStatusCheck(ticket.getStatus());
            ticket.setStatus(updatedStatus);
            ticketRepository.save(ticket);

            if ("CONFIRMED".equals(updatedStatus)) {
                producer.sendNotification("Your ticket " + ticket.getPnrNumber() + " is now CONFIRMED!");
            }
        }
        System.out.println("Scheduled job completed.");
    }

    private String simulateStatusCheck(String currentStatus) {
        // Simulated logic: WAITLISTED -> CONFIRMED, CONFIRMED remains, CANCELLED remains
        if ("WAITLISTED".equals(currentStatus)) return "CONFIRMED";
        return currentStatus;
    }
}
