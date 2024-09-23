package com.sritel.billing.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class PaymentDTO {
    @NotNull(message = "User Id cannot be null")
    private Long userId;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @NotNull(message = "Payment Method cannot be null")
    private String paymentMethod;

    @NotNull(message = "Payment Status cannot be null")
    private LocalDateTime paymentDate;

    private String transactionReference;
    private String notes;
}
