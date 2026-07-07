package com.fraud_remittance.transaction_service.repository;

import com.fraud_remittance.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
