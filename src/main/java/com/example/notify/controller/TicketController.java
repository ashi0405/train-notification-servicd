package com.example.notify.controller;

import com.example.notify.entity.Ticket;
import com.example.notify.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/submit")
    public ResponseEntity<Ticket> submitTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.saveTicket(ticket));
    }

    @GetMapping("/status/{pnrNumber}")
    public ResponseEntity<Ticket> getTicketStatus(@PathVariable String pnrNumber) {
        return ticketService.findByPnr(pnrNumber)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PNR not found"));
    }
}


