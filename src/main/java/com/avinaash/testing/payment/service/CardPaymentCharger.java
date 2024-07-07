package com.avinaash.testing.payment.service;

import com.avinaash.testing.payment.Currency;
import com.avinaash.testing.payment.model.CardPaymentCharge;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface CardPaymentCharger {
    CardPaymentCharge chargeCard(String cardSource, BigDecimal amount, Currency currency, String description);
}
