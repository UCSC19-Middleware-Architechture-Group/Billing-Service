package com.billingService.billingService.Repository;

import com.billingService.billingService.Entity.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {
}
