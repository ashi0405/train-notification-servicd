package com.example.notify.entity;

import com.example.notify.response.PnrResponse;
import lombok.Data;

import java.util.List;

@Data
public class PnrData {
    private String pnrNumber;
    private String dateOfJourney;
    private String trainNumber;
    private String trainName;
    private List<Passenger> passengerList;
}