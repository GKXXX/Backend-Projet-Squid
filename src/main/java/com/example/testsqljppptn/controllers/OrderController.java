package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
    public ResponseEntity getAllOrders(@RequestHeader("token") String token) {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    /**
    @GetMapping("/myOrders")
    public Iterable<Order> getMyOrders(@RequestParam Long idCustomer) {

        return
    }*/

    @GetMapping("/byId")
    public ResponseEntity getOrder(@RequestParam int id,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("Token d'authentification invalide");
        }
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byCustomer")
    public ResponseEntity getOrdersByCustomer(@RequestParam("idCustomer") int idCustomer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("Token d'authentification invalide");
        }
        return ResponseEntity.ok(orderRepository.getMyOrder(idCustomer));
    }

    @PostMapping()
    public ResponseEntity createOrder(@RequestBody Order order,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("Token d'authentification invalide");
        }
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity deleteOrder(@RequestParam int id,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("Token d'authentification invalide");
        }
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
