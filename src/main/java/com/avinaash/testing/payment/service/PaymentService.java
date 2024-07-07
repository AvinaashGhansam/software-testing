package com.avinaash.testing.payment.service;

import com.avinaash.testing.customer.repository.CustomerRepository;
import com.avinaash.testing.payment.Currency;
import com.avinaash.testing.payment.dto.PaymentRequest;
import com.avinaash.testing.payment.model.CardPaymentCharge;
import com.avinaash.testing.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private static final List<Currency> ACCEPTED_CURRENCIES = List.of(Currency.USD);
    private final CustomerRepository customerRepository;
    private  final PaymentRepository paymentRepository;
    private final CardPaymentCharger cardPaymentCharger;

    @Autowired
    public PaymentService(CustomerRepository customerRepository
            , PaymentRepository paymentRepository
            ,CardPaymentCharger cardPaymentCharger) {
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
        this.cardPaymentCharger = cardPaymentCharger;
    }

    public void chargeCard(UUID customerId, PaymentRequest paymentRequest) {
        boolean isCustomerFound = customerRepository.findById(customerId).isPresent();
        if (!isCustomerFound) {
            throw new IllegalStateException(String.format("Customer with id [%s] is not found", customerId));
        }

        boolean isCurrencySupported = ACCEPTED_CURRENCIES.stream().anyMatch(c -> c.equals(paymentRequest.getPayment().getCurrency()));

        if (!isCurrencySupported) {
            String msg = String.format("Currency [%s] not supported", paymentRequest.getPayment().getCurrency());
            throw new IllegalStateException(msg);
        }

        CardPaymentCharge cardPaymentCharge = cardPaymentCharger.chargeCard(paymentRequest.getPayment().getSource(),
                paymentRequest.getPayment().getAmount(),
                paymentRequest.getPayment().getCurrency(),
                paymentRequest.getPayment().getDescription());

        // If no payment
        if (!cardPaymentCharge.isCardDebited()) {
            throw  new IllegalStateException(String.format("Card not debited for customer %s", customerId));
        }

        // Insert payment
        paymentRequest.getPayment().setCustomerId(customerId);
        paymentRepository.save(paymentRequest.getPayment());
    }
}
