package com.fraud_remittance.transaction_service.repository;

import com.fraud_remittance.transaction_service.entity.Transaction;
import dto.transaction.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByCustomerId(Long customerId, Pageable pageable);
}
