package com.example.notify.service;

import com.example.notify.entity.Ticket;
import com.example.notify.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        ticket.setStatus("WAITLISTED");
        ticket.setLastCheckedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> findByPnr(String pnr) {
        return ticketRepository.findByPnrNumber(pnr);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

}


