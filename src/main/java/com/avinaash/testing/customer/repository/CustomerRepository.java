package com.avinaash.testing.customer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.avinaash.testing.customer.Customer;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    @Query(value = "SELECT id, name, phone_number "
            + "FROM customer where phone_number = :phone_number", nativeQuery = true)
    Optional<Customer> selectCustomerByPhoneNumber(@Param("phone_number") String phoneNumber);
    // TODO: Set up test class

}
