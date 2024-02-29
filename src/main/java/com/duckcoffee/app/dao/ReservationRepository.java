package com.duckcoffee.app.dao;

import com.duckcoffee.app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin("http://localhost:3000")
public interface ReservationRepository extends JpaRepository<Reservation , Long> {
    List<Reservation> findByBookingDate(LocalDate bookingDate);

    List<Reservation> findByBookingDateAndTableSeatIdIn(LocalDate bookingDate, List<Long> tableIds);

    List<Reservation> findByBookingDateAndStartTime(LocalDate bookingDate , LocalTime startTime);

}
