package com.avinaash.testing.payment.repository;

import com.avinaash.testing.payment.model.Payment;
import org.springframework.data.repository.CrudRepository;


public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
