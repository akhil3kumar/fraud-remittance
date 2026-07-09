package com.fraud_remittance.transaction_service.service.impl;

import com.fraud_remittance.transaction_service.dto.TransactionMapper;
import com.fraud_remittance.transaction_service.entity.Transaction;
import com.fraud_remittance.transaction_service.repository.TransactionRepository;
import com.fraud_remittance.transaction_service.service.TransactionService;
import dto.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public Page<TransactionResponse> getTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable)
                .map(transactionMapper::toTransactionResponse);
    }

    @Override
    public Page<TransactionResponse> getTransactionsByCustomer(Long customerId, Pageable pageable) {
        return transactionRepository.findByCustomerId(customerId,pageable)
                .map(transactionMapper::toTransactionResponse);

    }
}
