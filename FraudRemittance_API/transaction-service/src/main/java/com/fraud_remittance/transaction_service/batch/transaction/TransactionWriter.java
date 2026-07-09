package com.fraud_remittance.transaction_service.batch.transaction;

import com.fraud_remittance.transaction_service.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
@RequiredArgsConstructor
public class TransactionWriter {

    private final DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<Transaction> transactionItemWriter() {
        JdbcBatchItemWriter<Transaction> writer =
                new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);

        writer.setSql("""
                INSERT INTO transactions
                (transaction_id, customer_id, amount)
                VALUES
                (:transactionId, :customerId, :amount)
                ON CONFLICT (transaction_id)
                DO NOTHING
                """);

        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setAssertUpdates(false);
        writer.afterPropertiesSet();

        return writer;
    }
}
