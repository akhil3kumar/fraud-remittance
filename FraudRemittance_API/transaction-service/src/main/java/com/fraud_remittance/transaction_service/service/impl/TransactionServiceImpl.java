package com.fraud_remittance.transaction_service.service.impl;

import com.fraud_remittance.transaction_service.dto.TransactionMapper;
import com.fraud_remittance.transaction_service.entity.Transaction;
import com.fraud_remittance.transaction_service.repository.TransactionRepository;
import com.fraud_remittance.transaction_service.service.TransactionService;
import dto.transaction.TransactionRequest;
import dto.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;
    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Transaction transaction = transactionMapper.getTransactionEntity(request);

        Transaction savedTransaction =transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(savedTransaction);

    }

    private void validateTransactionRequest(TransactionRequest request) {


    }

    @Override
    public TransactionResponse getTransaction(Long transactionId) {
        return null;
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return List.of();
    }
}
