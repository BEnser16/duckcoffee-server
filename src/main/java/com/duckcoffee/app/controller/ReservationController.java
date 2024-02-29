package com.duckcoffee.app.controller;


import com.duckcoffee.app.dto.ReservationRequest;
import com.duckcoffee.app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/query-available-reservations")
    public ResponseEntity<Object> findAvailableReservations(@RequestParam LocalDate bookingDate , @RequestParam int peoples) {
        List<LocalTime> availableTimeSlots = reservationService.findAvailableTimeSlots(bookingDate , peoples);

        return ResponseEntity.ok(availableTimeSlots);
    }

    @PostMapping("/assign-table")
    public ResponseEntity<Object> assignTableForReservation(@RequestBody ReservationRequest request) {
        Long tableId = reservationService.assignTableForReservation(request.getBookingDate(), request.getStartTime(), request.getPeople());

        return ResponseEntity.ok(tableId);
    }

}
