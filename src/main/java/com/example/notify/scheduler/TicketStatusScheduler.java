package com.example.notify.scheduler;

import com.example.notify.emailservice.EmailService;
import com.example.notify.entity.Ticket;
import com.example.notify.repository.TicketRepository;
import com.example.notify.service.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusScheduler {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotificationProducer producer;

    @Value("${scheduler.enabled}")
    private boolean schedulerEnabled;


    @Scheduled(fixedRate = 1000 * 60 * 60 * 12) // Every 12 hours
    public void checkStatusAndNotify() {

        if (!schedulerEnabled) {
            System.out.println("Scheduler is disabled. Status change task will not run.");
            return; // If scheduler is disabled, do nothing.
        }

        System.out.println("Scheduled job started - checking status...");

        List<Ticket> tickets = ticketRepository.findAll();
        for (Ticket ticket : tickets) {
            // Simulate status check from API
            String oldStatus = ticket.getStatus();
            String updatedStatus = simulateStatusCheck(oldStatus);

            if (!oldStatus.equals(updatedStatus)) {
                ticket.setStatus(updatedStatus);
                ticketRepository.save(ticket);

                if ("CONFIRMED".equals(updatedStatus)) {
                    producer.sendNotification(ticket);
                }
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
