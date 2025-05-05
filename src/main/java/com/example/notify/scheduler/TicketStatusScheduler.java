package com.example.notify.scheduler;

import com.example.notify.emailservice.EmailService;
import com.example.notify.entity.Ticket;
import com.example.notify.pnr.PNRStatusClient;
import com.example.notify.repository.TicketRepository;
import com.example.notify.service.NotificationProducer;
import com.example.notify.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusScheduler {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotificationProducer producer;

    @Value("${scheduler.enabled}")
    private boolean schedulerEnabled;

    @Autowired
    private PNRStatusClient pnrStatusClient;


    @Scheduled(fixedRate = 1000 * 60 * 60 * 12) // Every 12 hours
    public void checkStatusAndNotify() {

        if (!schedulerEnabled) {
            System.out.println("Scheduler is disabled. Status change task will not run.");
            return; // If scheduler is disabled, do nothing.
        }

        System.out.println("Scheduled job started - checking status...");

        List<Ticket> tickets = ticketService.getAllTickets();

        for (Ticket ticket : tickets) {

            String oldStatus = ticket.getStatus();
            String updatedStatus = pnrStatusClient.checkPnrStatus(ticket.getPnrNumber());

            if (!oldStatus.equals(updatedStatus)) {
                ticket.setStatus(updatedStatus);
                ticketRepository.save(ticket);

                if ("CNF".equals(updatedStatus)) {
                    producer.sendNotification(ticket);
                }
            }
        }
        System.out.println("Scheduled job completed.");
    }

//    private String simulateStatusCheck(String currentStatus) {
//        // Simulated logic: WAITLISTED -> CONFIRMED, CONFIRMED remains, CANCELLED remains
//        if ("WAITLISTED".equals(currentStatus)) return "CONFIRMED";
//        return currentStatus;
//    }
}
