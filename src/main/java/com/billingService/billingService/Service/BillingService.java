package com.billingService.billingService.Service;

import com.billingService.billingService.DTO.BillingDTO;
import com.billingService.billingService.DTO.PaymentDTO;
import com.billingService.billingService.Entity.*;
import com.billingService.billingService.Repository.*;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingDetailsRepository billingRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    public BillingDetails getBillingDetails(Long billingId) {
        return billingRepository.findById(billingId).orElse(null);
    }

    public BillingDTO createBillingDetails(@Valid BillingDTO billingDetails) {
        BillingDetails billing = new BillingDetails();
        billing.setName(billingDetails.getName());
        billing.setDuration(billingDetails.getDuration());
        billing.setAmount(billingDetails.getAmount());
        billing.setDescription(billingDetails.getDescription());
        billing.setCreated_at(LocalDate.now());

        billingRepository.save(billing);

        return billingDetails;
    }

    public BillingDTO updateBillingDetails(@Valid Long billingId, BillingDTO billingDetails) {
        BillingDetails billing = billingRepository.findById(billingId).orElse(null);
        if (billing != null) {
            billing.setName(billingDetails.getName());
            billing.setDuration(billingDetails.getDuration());
            billing.setAmount(billingDetails.getAmount());
            billing.setDescription(billingDetails.getDescription());
            billing.setUpdated_at(LocalDate.now());

            billingRepository.save(billing);
        }

        return billingDetails;
    }
}
