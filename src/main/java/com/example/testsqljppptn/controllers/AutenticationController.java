package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private CustomerRepository customerRepository;

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
                Map<String,Object> payload = new HashMap<>();
                payload.put("email",loggingCustomer.get().getMail());
                payload.put("firstName",loggingCustomer.get().getFirstName());
                payload.put("lastName",loggingCustomer.get().getName());
                payload.put("id",loggingCustomer.get().getId());
                payload.put("isAdmin",loggingCustomer.get().isAdmin());

                Algorithm algorithm = Algorithm.HMAC512("Cadrillage-78");
                token = JWT.create().withPayload(payload).withIssuer("auth0").sign(algorithm);

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
        String emptyString ="";
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

        registeringCustomerInfo.setAddress(emptyString);

        registeringCustomerInfo.setName(emptyString);

        registeringCustomerInfo.setFirstName(emptyString);

        registeringCustomerInfo.setAddressComment(emptyString);

        registeringCustomerInfo.setCity(emptyString);

        registeringCustomerInfo.setPostalCode(0);

        registeringCustomerInfo.setCivility(emptyString);

        registeringCustomerInfo.setAdmin(false);

        registeringCustomerInfo.setPassword(hashedPassword);
        customerRepository.save(registeringCustomerInfo);
        return ResponseEntity.ok("OK");
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
