package com.example.testsqljppptn.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.testsqljppptn.entity.Contact;
import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.ContactRepository;
import com.example.testsqljppptn.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @PostMapping()
    public ResponseEntity postContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Demande de contact envoyée.");
    }

}
