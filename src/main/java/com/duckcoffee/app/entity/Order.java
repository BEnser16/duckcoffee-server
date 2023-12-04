package com.duckcoffee.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "order_items")
    private List<OrderItem> orderItems;

    @Column(name = "create_time")
    private LocalDateTime create_time;

    @Column(name = "lastupdate_time")
    private LocalDateTime last_update_time;

    @Column(name = "table_number")
    private int table_number;

    @Column(name = "total_price")
    private int total_price;

}
