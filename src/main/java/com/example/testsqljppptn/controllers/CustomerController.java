package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.OperatingSystemMXBean;
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

    @GetMapping()
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/byId")
    public ResponseEntity<Optional<Customer>> getCustomerById(@RequestParam int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        Iterable<Customer> listCustomer = customerRepository.findAll();
        if(IsUserAlreadyExist(listCustomer,customer.getMail())) {
            return ResponseEntity.internalServerError().body("Already existing user.");
        }
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(customer.getPassword().getBytes(StandardCharsets.UTF_8));
            hashedPassword = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur lors de l'encodage du mdp.");
        }
        customerRepository.save(customer);
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

    @PutMapping()
    public ResponseEntity<Optional<Customer>> editCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerToEdit = customerRepository.findByMail(customer.getMail());
        if (customerToEdit.isPresent()) {
            if (customer.getMail() != null) {
                customerToEdit.get().setMail(customer.getMail());
            }
            if (!customer.getPassword().equals(customerToEdit.get().getPassword())) {
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
            if (customer.isAdmin() != customerToEdit.get().isAdmin()) {
                customerToEdit.get().setAdmin(customer.isAdmin());
            }
            customerRepository.save(customerToEdit.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteCustomer(@RequestParam int id) {
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
