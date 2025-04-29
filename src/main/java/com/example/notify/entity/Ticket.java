package com.example.notify.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pnrNumber;

    private String userName;

    private String trainName;

    private String status; // Pending, Confirmed, Waitlisted, etc.

    private LocalDate journeyDate;

    private LocalDateTime lastCheckedAt;


}

