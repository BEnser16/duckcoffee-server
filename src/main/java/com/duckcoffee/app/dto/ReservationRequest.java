package com.duckcoffee.app.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRequest {
    private LocalDate bookingDate;
    private int people;
    private LocalTime startTime;
}
