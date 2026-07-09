package com.fraud_remittance.transaction_service.batch.transactionMetadata;

import com.fraud_remittance.transaction_service.entity.Transaction;
import com.fraud_remittance.transaction_service.entity.TransactionMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class TransactionMetaDataWriter {
    private final DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<TransactionMetadata> transactionMetadataItemWriter() {
        JdbcBatchItemWriter<TransactionMetadata> writer =
                new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);

        writer.setSql("""
                INSERT INTO transaction_metadata
                (transaction_id, transaction_time, merchant_id)
                VALUES
                (:transactionId, :timestamp, :merchantId)
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
