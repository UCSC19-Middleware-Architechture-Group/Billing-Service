package com.sritel.billing.Service;

import com.sritel.billing.DTO.PaymentDTO;
import com.sritel.billing.Entity.PaymentHistory;
import com.sritel.billing.Repository.PaymentHistoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    public PaymentDTO processPayment(@Valid PaymentDTO paymentRequest) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setUserId(paymentRequest.getUserId());
        paymentHistory.setAmount(paymentRequest.getAmount());
        paymentHistory.setPaymentDate(paymentRequest.getPaymentDate());
        paymentHistory.setPaymentMethod(paymentRequest.getPaymentMethod());
        paymentHistory.setTransactionReference(paymentRequest.getTransactionReference());
        paymentHistory.setNotes(paymentRequest.getNotes());

        paymentHistory.setPaymentStatus("SUCCESS");
        paymentHistory.setCurrency("LKR");
        paymentHistory.setCreatedAt(LocalDateTime.now());
        paymentHistory.setUpdatedAt(null);

        paymentHistoryRepository.save(paymentHistory);
        return paymentRequest;
    }

    public List<PaymentHistory> getPaymentHistory(Long userId) {
        return paymentHistoryRepository.findByUserId(userId);
    }

    public PaymentDTO updatePayment(Long paymentId, @Valid PaymentDTO paymentHistory) {
        PaymentHistory payment = paymentHistoryRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            payment.setUserId(paymentHistory.getUserId());
            payment.setAmount(paymentHistory.getAmount());
            payment.setPaymentDate(paymentHistory.getPaymentDate());
            payment.setPaymentMethod(paymentHistory.getPaymentMethod());
            payment.setTransactionReference(paymentHistory.getTransactionReference());
            payment.setNotes(paymentHistory.getNotes());
            payment.setUpdatedAt(LocalDateTime.now());

            paymentHistoryRepository.save(payment);
        }

        return paymentHistory;
    }
}
