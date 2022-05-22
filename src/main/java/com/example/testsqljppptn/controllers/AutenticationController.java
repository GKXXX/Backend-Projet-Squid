package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.CustomerRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping(value = "/connection_mobile",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity connectMobile(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("json");
        System.out.println(email);
        Optional<Customer> loggingCustomer = customerRepository.findByMail(email);
        if (!loggingCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            hashedPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur lors de l'encodage du mdp.");
        }
        if (loggingCustomer.get().getPassword().equals(hashedPassword)) {
            String token = "";
            try {
                Algorithm algorithm = Algorithm.HMAC512("Cadrillage-78");
                token = JWT.create().withIssuer("auth0").sign(algorithm);
            } catch (JWTCreationException exception) {
                throw exception;
            }

            return ResponseEntity.ok().body( token );
        } else {
            return ResponseEntity.internalServerError().body("Mot de passe incorrect");
        }

    }

    @PostMapping(value = "/connection",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity connect(@RequestBody Customer connectingCustomerInfo) {
        Optional<Customer> loggingCustomer = customerRepository.findByMail(connectingCustomerInfo.getMail());
        System.out.println(connectingCustomerInfo.getMail());
        System.out.println(loggingCustomer.isPresent());
        if (!loggingCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(connectingCustomerInfo.getPassword().getBytes(StandardCharsets.UTF_8));
            hashedPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur lors de l'encodage du mdp.");
        }
        if (loggingCustomer.get().getPassword().equals(hashedPassword)) {
            String token = "";
            try {
                Algorithm algorithm = Algorithm.HMAC512("Cadrillage-78");
                token = JWT.create().withIssuer("auth0").sign(algorithm);
            } catch (JWTCreationException exception) {
                throw exception;
            }

            return ResponseEntity.ok().body( token );
        } else {
            return ResponseEntity.internalServerError().body("Mot de passe incorrect");
         }

    }

    @PostMapping("/inscription")
    public ResponseEntity inscription(@RequestBody Customer registeringCustomerInfo) {
        Iterable<Customer> listCustomer = customerRepository.findAll();
        if(IsUserAlreadyExist(listCustomer,registeringCustomerInfo.getMail())) {
            return ResponseEntity.internalServerError().body("Already existing user.");
        }
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(registeringCustomerInfo.getPassword().getBytes(StandardCharsets.UTF_8));
            hashedPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur lors de l'encodage du mdp.");
        }
        registeringCustomerInfo.setPassword(hashedPassword);
        customerRepository.save(registeringCustomerInfo);
        return ResponseEntity.ok().build();
    }

    private boolean IsUserAlreadyExist(Iterable<Customer> listCustomer,String mail) {
        for (Customer customer: listCustomer) {
            if(customer.getMail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

}
