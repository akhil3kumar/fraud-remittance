package com.fraud_remittance.transaction_service.service;

import dto.transaction.TransactionMetadataResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionMetadataService {
    Page<TransactionMetadataResponse> getTransactionMetadata(Pageable pageable);
    Page<TransactionMetadataResponse> getTransactionsMetadataByTransaction(Long transactionId, Pageable pageable);
}
