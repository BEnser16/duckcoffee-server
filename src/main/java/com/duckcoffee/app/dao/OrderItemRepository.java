package com.duckcoffee.app.dao;

import com.duckcoffee.app.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")

public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {

}
