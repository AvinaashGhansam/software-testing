package com.avinaash.testing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinaash.testing.dto.CustomerRegistrationRequest;
import com.avinaash.testing.repository.CustomerRepository;

@Service
public class CustomerRegistrationService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerRegistrationService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void registerNewCustomer(CustomerRegistrationRequest request) {
        // check if customer exist

    }

}
