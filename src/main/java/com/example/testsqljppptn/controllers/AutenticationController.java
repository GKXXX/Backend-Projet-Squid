package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/connection")
    public ResponseEntity connect(@RequestBody Customer connectingCustomerInfo) {
        Optional<Customer> loggingCustomer = customerRepository.findByMail(connectingCustomerInfo.getMail());
        if (loggingCustomer.isEmpty()) {
            return ResponseEntity.ok("utilisateur introuvable");
        }
        if (loggingCustomer.get().getPassword().equals(connectingCustomerInfo.getPassword())) {
            String token = "";
            try {
                Algorithm algorithm = Algorithm.HMAC512("Cadrillage-78");
                token = JWT.create().withIssuer("auth0").sign(algorithm);
            } catch (JWTCreationException exception) {

            }

            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.ok().body("Mot de passe incorrect");
         }

    }

    /**@PostMapping("/inscription")
    public ResponseEntity inscription(@RequestBody String mail,@RequestBody String password) {
        Iterable<Customer> listCustomer = customerRepository.findAll();
        listCustomer.
        customerRepository.save(new Customer(mail,password));
        return ResponseEntity;
    }*/

}
