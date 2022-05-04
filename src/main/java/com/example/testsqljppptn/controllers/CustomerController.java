package com.example.testsqljppptn.controllers;

import com.example.testsqljppptn.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController   {

    @Autowired
    private CustomerRepository customerRepository;

}
