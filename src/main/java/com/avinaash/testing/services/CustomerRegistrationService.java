package com.avinaash.testing.services;

import com.avinaash.testing.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinaash.testing.dto.CustomerRegistrationRequest;
import com.avinaash.testing.repository.CustomerRepository;

import java.util.Optional;

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
        customerRepository.save(request.getCustomer());
    }

}
