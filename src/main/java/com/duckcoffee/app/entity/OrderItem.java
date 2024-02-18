package com.duckcoffee.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    private MenuItem menuItem;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "remark")
    private String remark;

    @Column(name = "sugar")
    private String sugar;

    @Column(name = "ice")
    private String ice;

    @ManyToOne
    @JoinColumn(name = "orderform_id" , referencedColumnName = "id")
    private OrderForm orderForm;


}
