package com.avinaash.testing.payment.dto;

import com.avinaash.testing.payment.model.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequest {
    private final Payment payment;

    public PaymentRequest(@JsonProperty Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "payment=" + payment +
                '}';
    }
}
