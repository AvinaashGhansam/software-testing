package com.avinaash.testing.customer.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avinaash.testing.customer.dto.CustomerRegistrationRequest;

@RestController
@RequestMapping("api/v1/customer-registration")
public class CustomerRegistrationController {

    @PutMapping
    public void registerNewCustomer(@RequestBody @Valid CustomerRegistrationRequest request) {

    }

}
