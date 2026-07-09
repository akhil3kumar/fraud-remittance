package com.fraud_remittance.transaction_service.dto;

import com.fraud_remittance.transaction_service.entity.Transaction;
import com.fraud_remittance.transaction_service.entity.TransactionStatus;
import dto.transaction.TransactionRequest;
import dto.transaction.TransactionResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionMapper {



    public Transaction getTransactionEntity(TransactionRequest request) {
        return Transaction.builder()
                .customerId(request.customerId())
                .amount(request.amount())
                .build();
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .customerId(transaction.getCustomerId())
                .amount(transaction.getAmount())
                .build();
    }
}


