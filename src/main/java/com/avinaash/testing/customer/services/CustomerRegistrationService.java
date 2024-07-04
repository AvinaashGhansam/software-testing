package com.avinaash.testing.customer.services;

import com.avinaash.testing.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinaash.testing.customer.dto.CustomerRegistrationRequest;
import com.avinaash.testing.customer.repository.CustomerRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerRegistrationService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRegistrationService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public void registerNewCustomer(CustomerRegistrationRequest request) {
        Optional<Customer> customerOptional = customerRepository.selectCustomerByPhoneNumber(request.getCustomer().getPhoneNumber());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getName().equals(request.getCustomer().getName())) {
                return;
            }
            throw new IllegalStateException(String.format("Phone Number [%s] is taken", customer.getPhoneNumber()));
        }

        if (request.getCustomer().getId() == null) {
            request.getCustomer().setId(UUID.randomUUID());
        }
        customerRepository.save(request.getCustomer());
    }

}
