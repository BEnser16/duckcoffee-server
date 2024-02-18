package com.duckcoffee.app.controller;

import com.duckcoffee.app.dao.OrderItemRepository;
import com.duckcoffee.app.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customOrderItems")
public class OrderItemController {
    @Autowired
    private OrderItemRepository repository;

    @PostMapping
    public ResponseEntity<Object> createMultipleOrderItems(@RequestBody List<OrderItem> orderItems) {
        List<OrderItem> savedItems = repository.saveAll(orderItems);
        // 處理返回值
        return ResponseEntity.ok(savedItems);
    }
}
