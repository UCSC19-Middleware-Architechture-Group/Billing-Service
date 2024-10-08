package com.sritel.billing.Controller.v1;

import com.sritel.billing.DTO.BillingDTO;
import com.sritel.billing.Service.BillingService;
import com.sritel.billing.Service.EmailService;
import com.sritel.billing.Entity.BillingDetails;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/billing")
@CrossOrigin
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private EmailService emailService;

    // Get billing details/prices
    @GetMapping("/{billingId}")
    public ResponseEntity<BillingDetails> getBillingDetails(@PathVariable Long billingId) {
        BillingDetails billingDetails = billingService.getBillingDetails(billingId);
        return ResponseEntity.ok(billingDetails);
    }

    // Create billing details/prices
    @PostMapping
    public ResponseEntity<BillingDTO> createBillingDetails(@Valid @RequestBody BillingDTO billingDetails) {
        return ResponseEntity.status(201).body(billingService.createBillingDetails(billingDetails));
    }

    // Update billing details/prices
    @PutMapping("/{billingId}")
    public ResponseEntity<BillingDTO> updateBillingDetails(@Valid @PathVariable Long billingId, @RequestBody BillingDTO billingDetails) {
        return ResponseEntity.ok(billingService.updateBillingDetails(billingId, billingDetails));
    }

    // Generate and email monthly statements
    @PostMapping("/email/statement")
    public ResponseEntity<String> sendMonthlyStatement(@RequestParam String toEmail, @RequestParam Long userId) {
        emailService.sendMonthlyStatement(toEmail, userId);
        return ResponseEntity.ok("Monthly statement sent successfully.");
    }
}