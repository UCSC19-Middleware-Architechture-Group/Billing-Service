package com.sritel.billing.Controller.v1;

import com.sritel.billing.DTO.PaymentDTO;
import com.sritel.billing.Entity.PaymentHistory;

import com.sritel.billing.Service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    // Make payments
    @PostMapping
    public ResponseEntity<PaymentDTO> makePayment(@Valid @RequestBody PaymentDTO paymentRequest) {
        return ResponseEntity.status(201).body(paymentService.processPayment(paymentRequest));
    }

    // Retrieve payment histories
    @GetMapping("/{userId}")
    public ResponseEntity<List<PaymentHistory>> getPaymentHistory(@PathVariable Long userId) {
        List<PaymentHistory> paymentHistories = paymentService.getPaymentHistory(userId);
        return ResponseEntity.ok(paymentHistories);
    }

    // Update payment
    @PutMapping
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long paymentId, @Valid @RequestBody PaymentDTO paymentHistory) {
        return ResponseEntity.status(201).body(paymentService.updatePayment(paymentId, paymentHistory));
    }
}