package com.avinaash.testing.dto;

import com.avinaash.testing.customer.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRegistrationRequest {
    private final Customer customer;

    public CustomerRegistrationRequest(@JsonProperty("customer") Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "CustomerRegistrationRequest [customer=" + customer + "]";
    }

}
