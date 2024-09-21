package com.billingService.billingService.Entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class PaymentRequest {

    private Long userId;
    private BigDecimal amount;
    private LocalDate date;
}
