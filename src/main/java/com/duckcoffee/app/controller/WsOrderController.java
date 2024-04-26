package com.duckcoffee.app.controller;


import com.duckcoffee.app.entity.OrderForm;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsOrderController {

    @MessageMapping("/order")
    @SendTo("/topic/orders")
    public OrderForm updateOrder(OrderForm orderForm) {

        return orderForm;
    }
}
