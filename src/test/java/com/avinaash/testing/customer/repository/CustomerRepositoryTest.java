package com.avinaash.testing.customer.repository;

import com.avinaash.testing.customer.Customer;
import com.avinaash.testing.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest(properties = "spring.jpa.properties.javax.persistence.validation.mode=none")
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository underTest;

    @Test
    void selectCustomerByPhoneNumber() {
        // Given
        UUID id = UUID.randomUUID();
        String john = "john";
        String number = "123";
        Customer customer = new Customer(id, john, number);

        // When
        underTest.save(customer);

        // Then
        Optional<Customer>optionalCustomer = underTest.selectCustomerByPhoneNumber(number);
        assertThat(optionalCustomer).isPresent().hasValueSatisfying(c -> {
            assertThat(c).isEqualToComparingFieldByField(customer);
        });
    }

    @Test
    void selectCustomerByPhoneNumberWhenNumberDoesNotExists() {
        // Given
        String phoneNumber = "000";


        // When
        // Then
        Optional<Customer>optionalCustomer = underTest.selectCustomerByPhoneNumber(null);
        assertThat(optionalCustomer).isNotPresent();
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

    @Test
    void itShouldNotSaveCustomerWhenNameIsNull() {
        // Given
        UUID id = UUID.randomUUID();
        String number = "123";
        Customer customer = new Customer(id, null, number);

        // When // Then
        assertThatThrownBy(() -> underTest.save(customer)).hasMessageContaining("not-null property references a null or transient value : com.avinaash.testing.customer.Customer.name; nested exception is org.hibernate.PropertyValueException: not-null property references a null or transient value : com.avinaash.testing.customer.Customer.name")
                .isInstanceOf(DataIntegrityViolationException.class);

    }

    @Test
    void itShouldNotSaveCustomerPhoneNumberWhenNull() {
        // Given
        UUID id = UUID.randomUUID();
        String john = "John";

        Customer customer = new Customer(id, john, null);

        // When // Then
        assertThatThrownBy(() -> underTest.save(customer)).hasMessageContaining("not-null property references a null or transient value : com.avinaash.testing.customer.Customer.phoneNumber; nested exception is org.hibernate.PropertyValueException: not-null property references a null or transient value : com.avinaash.testing.customer.Customer.phoneNumber")
                .isInstanceOf(DataIntegrityViolationException.class);

    }
}