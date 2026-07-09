package com.fraud_remittance.transaction_service.batch;

import com.fraud_remittance.transaction_service.entity.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionProcessor
        implements ItemProcessor<Transaction, Transaction> {

    @Override
    public Transaction process(Transaction transaction) {

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0 ) {
            return null;
        }

        return transaction;
    }
}
