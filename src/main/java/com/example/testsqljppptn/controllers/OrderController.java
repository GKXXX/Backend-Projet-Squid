package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Order;
import com.example.testsqljppptn.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/byId")
    public ResponseEntity<Optional<Order>> getOrder(@RequestParam Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteOrder(@RequestParam Long id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
