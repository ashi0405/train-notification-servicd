package com.example.notify.purge;

import com.example.notify.entity.Ticket;
import com.example.notify.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurgeOldTicketsJob {

    @Autowired
    private TicketRepository ticketRepository;

    @Value("${purge.job.enabled}")
    private boolean purgeJobEnabled;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24) // Every 24 hours
    public void purgeOldTickets() {

        if (!purgeJobEnabled) {
            System.out.println("Scheduler is disabled. Purge Task will not run.");
            return; // If scheduler is disabled, do nothing.
        }

        System.out.println("Purge job started.");
        List<Ticket> tickets = ticketRepository.findAll();
        LocalDate today = LocalDate.now();
        for (Ticket ticket : tickets) {
            if (ticket.getJourneyDate().isBefore(today)) {
                ticketRepository.delete(ticket);
                System.out.println("\u274C Deleted ticket with PNR: " + ticket.getPnrNumber());
            }
        }
        System.out.println("Purge job completed.");
    }
}
