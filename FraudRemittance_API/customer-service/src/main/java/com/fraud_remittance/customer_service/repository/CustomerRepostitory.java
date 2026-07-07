package com.fraud_remittance.customer_service.repository;

import com.fraud_remittance.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepostitory extends JpaRepository<Customer, Long> {
}
