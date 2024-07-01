package com.avinaash.testing.services;

import com.avinaash.testing.customer.Customer;
import com.avinaash.testing.dto.CustomerRegistrationRequest;
import com.avinaash.testing.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class CustomerRegistrationServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Captor
    private ArgumentCaptor<Customer>customerArgumentCaptor;
    private CustomerRegistrationService undertest;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        undertest = new CustomerRegistrationService(customerRepository);
    }

    @Test
    void itShouldSaveNewCustomer() {
        // Given
        String phoneNumber = "123";
        Customer customer = new Customer(UUID.randomUUID(), "John", phoneNumber);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);

        // No customer with phone number passed
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.empty());

        // When
        undertest.registerNewCustomer(request);

        // Then
        then(customerRepository).should().save(customerArgumentCaptor.capture());
        Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();
        assertThat(customerArgumentCaptorValue).isEqualToComparingFieldByField(customer);
    }
}