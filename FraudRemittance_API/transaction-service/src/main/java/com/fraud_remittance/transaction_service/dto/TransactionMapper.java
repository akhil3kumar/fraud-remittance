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
                .beneficiaryName(request.beneficiaryName())
                .beneficiaryAccount(request.beneficiaryAccount())
                .destinationCountry(request.destinationCountry())
                .amount(request.amount())
                .currency(request.currency())
                .status(TransactionStatus.PENDING_REVIEW)
                .riskScore(0)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .customerId(transaction.getCustomerId())
                .amount(transaction.getAmount())
                .destinationCountry(transaction.getDestinationCountry())
                .status(transaction.getStatus().toString())
                .riskScore(transaction.getRiskScore())
                .build();
    }
}


