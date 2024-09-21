package com.billingService.billingService.Service;

import com.billingService.billingService.Entity.*;
import com.billingService.billingService.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    @Autowired
    private BillingDetailsRepository billingRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    public BillingDetails getBillingDetails(Long billingId) {
        return billingRepository.findById(billingId).orElse(null);
    }

    public void processPayment(PaymentRequest paymentRequest) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setUserId(paymentRequest.getUserId());
        paymentHistory.setAmount(paymentRequest.getAmount());
        paymentHistory.setDate(paymentRequest.getDate());

        paymentHistoryRepository.save(paymentHistory);
    }

    public List<PaymentHistory> getPaymentHistory(Long userId) {
        return paymentHistoryRepository.findByUserId(userId);
    }

    public void createBillingDetails(BillingDetails billingDetails) {
        billingRepository.save(billingDetails);
    }
}
