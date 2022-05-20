package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Order;
import com.example.testsqljppptn.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping()
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @DeleteMapping()
    public ResponseEntity deleteOrder(@RequestParam Long id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
