package com.example.notify.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "PNR Number is required.")
    private String pnrNumber;

    @Column(nullable = false)
    @NotNull(message = "UserName is required.")
    private String userName;

    @Column(nullable = false)
    @NotNull(message = "TrainName is required.")
    private String trainName;

    private String status; // Pending, Confirmed, Waitlisted, etc.

    @Column(nullable = false)
    @NotNull(message = "JourneyDate is required.")
    @JsonFormat(pattern = "yyyy-MM-dd") // Specify the date format
    private LocalDate journeyDate;


    @NotNull(message = "Email is required.")
    @Email(message = "Invalid email format.")
    @Column(nullable = false)
    private String email;
}

