package com.example.testsqljppptn.service;

import com.example.testsqljppptn.entity.Customer;
import com.example.testsqljppptn.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


}
