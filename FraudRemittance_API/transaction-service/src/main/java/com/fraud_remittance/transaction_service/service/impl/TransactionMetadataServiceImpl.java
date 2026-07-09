package com.fraud_remittance.transaction_service.service.impl;

import com.fraud_remittance.transaction_service.dto.TransactionMetadataMapper;
import com.fraud_remittance.transaction_service.repository.TransactionMetadataRepository;
import com.fraud_remittance.transaction_service.service.TransactionMetadataService;
import dto.transaction.TransactionMetadataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionMetadataServiceImpl implements TransactionMetadataService {
    private final TransactionMetadataRepository transactionMetadataRepository;
    private final TransactionMetadataMapper transactionMetadataMapper;
    @Override
    public Page<TransactionMetadataResponse> getTransactionMetadata(Pageable pageable) {
        return transactionMetadataRepository.findAll(pageable)
                .map(transactionMetadataMapper::toTransactionResponse);
    }

    @Override
    public Page<TransactionMetadataResponse> getTransactionsMetadataByTransaction(Long transactionId, Pageable pageable) {
        return transactionMetadataRepository.findByTransactionId(transactionId,pageable)
                .map(transactionMetadataMapper::toTransactionResponse);
    }
}
