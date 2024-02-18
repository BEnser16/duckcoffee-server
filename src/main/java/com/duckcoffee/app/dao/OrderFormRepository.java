package com.duckcoffee.app.dao;

import com.duckcoffee.app.entity.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")

public interface OrderFormRepository extends JpaRepository<OrderForm, Long> {

}
