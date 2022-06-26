package com.example.testsqljppptn.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController   {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Récupère la liste de tout les utilisateurs
     * @return
     */
    @JsonIgnore
    @GetMapping()
    public @ResponseBody
    ResponseEntity getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    /**
     * Récupère l'utilisateur lié à l'id donné
     * @param id
     * @return
     */
    @GetMapping("/byId")
    public @ResponseBody ResponseEntity getCustomerById(@RequestParam int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Ajoute un utilisateur en base de donnée
     * @param customer
     * @param token
     * @return
     */
    @PostMapping()
    public ResponseEntity createCustomer(@RequestBody Customer customer,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        Iterable<Customer> listCustomer = customerRepository.findAll();
        if(IsUserAlreadyExist(listCustomer,customer.getMail())) {
            return ResponseEntity.internalServerError().body("{\"error\":\"Already existing user.\"}");
        }
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(customer.getPassword().getBytes(StandardCharsets.UTF_8));
            hashedPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("{\"error\":\"Erreur lors de l'encodage du mdp.\"}");
        }
        customer.setPassword(hashedPassword);

        customer.setAddress("");

        customer.setName("");

        customer.setFirstName("");

        customer.setAddressComment("");

        customer.setCity("");

        customer.setPostalCode(0);

        customer.setCivility("");

        customer.setAdmin(false);

        customerRepository.save(customer);
        return ResponseEntity.ok().build();
    }

    /**
     * renvoie true si l'utilisateur existe en base donnée sinon renvoie false
     * @param listCustomer
     * @param mail
     * @return
     */
    private boolean IsUserAlreadyExist(Iterable<Customer> listCustomer,String mail) {
        for (Customer customer: listCustomer) {
            if(customer.getMail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Modifie l'utilisateur liéé à l'id donné
     * @param id
     * @param customer
     * @return
     */
    @PutMapping()
    public ResponseEntity<Optional<Customer>> editCustomer(@RequestParam int id,@RequestBody Customer customer) {
        Optional<Customer> customerToEdit = customerRepository.findById(id);
        if (customerToEdit.isPresent()) {
            if (customer.getMail() != null) {
                customerToEdit.get().setMail(customer.getMail());
            }
            if (customer.getPassword() != null && !customer.getPassword().equals("")) {
                String hashedPassword = "";
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-512");
                    digest.reset();
                    digest.update(customer.getPassword().getBytes(StandardCharsets.UTF_8));
                    hashedPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.internalServerError().build();
                }
                customerToEdit.get().setPassword(hashedPassword);
            }
            if(customer.getAddress() != null) {
                customerToEdit.get().setAddress(customer.getAddress());
            }
            if (customer.getName() != null) {
                customerToEdit.get().setName(customer.getName());
            }
            if (customer.getFirstName() != null) {
                customerToEdit.get().setFirstName(customer.getFirstName());
            }
            if (customer.getAddressComment() != null) {
                customerToEdit.get().setAddressComment(customer.getAddressComment());
            }
            if (customer.getCity() != null) {
                customerToEdit.get().setCity(customer.getCity());
            }
            if (customer.getPostalCode() != 0) {
                customerToEdit.get().setPostalCode(customer.getPostalCode());
            }

            if(customer.getCivility() != null) {
                customerToEdit.get().setCivility(customer.getCivility());
            }
            if (customer.isAdmin() != customerToEdit.get().isAdmin()) {
                customerToEdit.get().setAdmin(customer.isAdmin());
            }
            customerRepository.save(customerToEdit.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Modifie le mot de passe de l'utilisateur lié à l'id donné
     * @param oldPassword
     * @param newPassword
     * @param idCustomer
     * @return
     */
    @PutMapping("/editPassword")
    public ResponseEntity editPassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,@RequestParam("idCustomer") int idCustomer) {
        String hashedPasswordOldPassword = "";
        Optional<Customer> customerToEdit = customerRepository.findById(idCustomer);
        if (customerToEdit.isPresent()) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-512");
                digest.reset();
                digest.update(oldPassword.getBytes(StandardCharsets.UTF_8));
                hashedPasswordOldPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
            if (hashedPasswordOldPassword.equals(customerToEdit.get().getPassword())) {
                String hashedNewPassword = "";
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-512");
                    digest.reset();
                    digest.update(newPassword.getBytes(StandardCharsets.UTF_8));
                    hashedNewPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.internalServerError().build();
                }
                customerToEdit.get().setPassword(hashedNewPassword);
                customerRepository.save(customerToEdit.get());
                return ResponseEntity.ok("Mot de passe modifié.");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"Ancien mot de passe incorrect.\"}");
        }
        return ResponseEntity.ok("{\"error\":\"Utilisateur introuvable.\"}");
    }

    /**
     * Supprime l'utilisateur lié à l'id donné
     * @param id
     * @param token
     * @return
     */
    @DeleteMapping()
    public ResponseEntity deleteCustomer(@RequestParam int id,@RequestHeader("token") String token) {
        try {
            Algorithm algo = Algorithm.HMAC512("Cadrillage-78");
            JWTVerifier verifier = JWT.require(algo).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.ok("{\"error\":\"Token d'authentification invalide\"}");
        }
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
