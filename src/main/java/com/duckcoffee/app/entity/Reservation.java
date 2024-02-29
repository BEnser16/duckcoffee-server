package com.duckcoffee.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_phone")
    private String personPhone;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableSeat tableSeat;

}
