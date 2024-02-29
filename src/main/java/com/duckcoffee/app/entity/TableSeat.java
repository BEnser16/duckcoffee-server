package com.duckcoffee.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "table_seat")
public class TableSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_number")
    private int tableNumber; // 用于业务逻辑的桌号

    @Column(name = "seat")
    private int seat;

    @OneToMany(mappedBy = "tableSeat")
    private List<Reservation> reservations;

}
