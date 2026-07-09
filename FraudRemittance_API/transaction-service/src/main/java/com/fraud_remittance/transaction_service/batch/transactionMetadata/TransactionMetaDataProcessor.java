package com.fraud_remittance.transaction_service.batch.transactionMetadata;

import com.fraud_remittance.transaction_service.entity.TransactionMetadata;
import com.fraud_remittance.transaction_service.repository.TransactionMetadataRepository;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class TransactionMetaDataProcessor implements ItemProcessor<TransactionMetadata,TransactionMetadata> {

    @Override
    public TransactionMetadata process(TransactionMetadata metadata) throws Exception {
        if (metadata.getTransactionId() == null) {
            return null; // skip record
        }

        if (metadata.getMerchantId() == null) {
            return null; // skip record
        }

        return metadata;
    }
}
