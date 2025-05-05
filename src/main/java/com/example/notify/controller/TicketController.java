package com.example.notify.controller;

import com.example.notify.entity.Ticket;
import com.example.notify.response.ErrorResponse;
import com.example.notify.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/submit")
    public ResponseEntity<Object> submitTicket(@RequestBody @Valid Ticket ticket) throws Exception {
        try {
            Ticket savedTicket = ticketService.saveTicket(ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket); // Successfully created ticket
        } catch (Exception e) {
            // Return the error message in a structured response
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); // Send error response
        }
    }

    @GetMapping("/status/{pnrNumber}")
    public ResponseEntity<Ticket> getTicketStatus(@PathVariable String pnrNumber) {
        return ticketService.findByPnr(pnrNumber)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PNR not found"));
    }
}


