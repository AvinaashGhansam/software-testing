package com.avinaash.testing.repository;

import com.avinaash.testing.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository underTest;

    @Test
    void selectCustomerByPhoneNumber() {
        // Given
        // Set up your objects and prepare the environment.

        // When
        // Perform the actual work of the test.

        // Then
        // Assert the results and verify outputs.
    }

    @Test
    void itShouldSaveCustomer() {
        // Given
        UUID id = UUID.randomUUID();
        String john = "john";
        String number = "123";
        Customer customer = new Customer(id, john, number);
       // When
        underTest.save(customer);
        // Then
        Optional<Customer>optionalCustomer = underTest.findById(id);
        assertThat(optionalCustomer)
                .isPresent().hasValueSatisfying(c -> {
                    /*assertThat(c.getId()).isEqualTo(id);
                    assertThat(c.getName()).isEqualTo(john);
                    assertThat(c.getPhoneNumber()).isEqualTo(number);*/
                    assertThat(c).isEqualToComparingFieldByField(customer);
                });
    }
}