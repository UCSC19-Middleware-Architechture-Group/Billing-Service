package com.billingService.billingService.Controller.v1;

import com.billingService.billingService.Service.BillingService;
import com.billingService.billingService.Service.EmailService;
import com.billingService.billingService.Entity.BillingDetails;
import com.billingService.billingService.Entity.PaymentRequest;
import com.billingService.billingService.Entity.PaymentHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private EmailService emailService;

    // Get billing details/prices
    @GetMapping("/details/{billingId}")
    public ResponseEntity<BillingDetails> getBillingDetails(@PathVariable Long billingId) {
        BillingDetails billingDetails = billingService.getBillingDetails(billingId);
        return ResponseEntity.ok(billingDetails);
    }

    // Create billing details/prices
    @PostMapping("/details")
    public ResponseEntity<BillingDetails> createBillingDetails(@RequestBody BillingDetails billingDetails) {
        billingService.createBillingDetails(billingDetails);
        return ResponseEntity.ok(billingDetails);
    }

    // Make payments
    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest paymentRequest) {
        billingService.processPayment(paymentRequest);
        return ResponseEntity.ok("Payment processed successfully.");
    }

    // Retrieve payment histories
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<PaymentHistory>> getPaymentHistory(@PathVariable Long userId) {
        List<PaymentHistory> paymentHistories = billingService.getPaymentHistory(userId);
        return ResponseEntity.ok(paymentHistories);
    }

    // Generate and email monthly statements
    @PostMapping("/email/statement")
    public ResponseEntity<String> sendMonthlyStatement(@RequestParam String toEmail,
                                                       @RequestParam String subject,
                                                       @RequestParam String body,
                                                       @RequestParam Long userId) {
        try {
            emailService.sendMonthlyStatement(toEmail, subject, body, userId);
            return ResponseEntity.ok("Monthly statement sent successfully.");
        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}