package com.fraud_remittance.transaction_service.service;


import dto.transaction.TransactionRequest;
import dto.transaction.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    Page<TransactionResponse> getTransactions(
            Pageable pageable);

    Page<TransactionResponse> getTransactionsByCustomer(
            Long customerId,
            Pageable pageable);
}
