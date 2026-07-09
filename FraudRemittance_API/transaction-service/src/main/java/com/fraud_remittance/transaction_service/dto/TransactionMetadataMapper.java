package com.fraud_remittance.transaction_service.dto;

import com.fraud_remittance.transaction_service.entity.TransactionMetadata;
import dto.transaction.TransactionMetadataResponse;
import dto.transaction.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMetadataMapper {

    public TransactionMetadataResponse toTransactionResponse(TransactionMetadata transactionMetadata) {
        return TransactionMetadataResponse.builder()
                .transactionId(transactionMetadata.getTransactionId())
                .timestamp(transactionMetadata.getTimestamp())
                .merchantId(transactionMetadata.getMerchantId())
                .build();
    }
}
