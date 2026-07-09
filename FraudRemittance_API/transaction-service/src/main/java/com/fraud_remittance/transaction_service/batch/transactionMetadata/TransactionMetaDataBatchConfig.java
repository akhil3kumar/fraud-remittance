package com.fraud_remittance.transaction_service.batch.transactionMetadata;

import com.fraud_remittance.transaction_service.entity.Transaction;
import com.fraud_remittance.transaction_service.entity.TransactionMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class TransactionMetaDataBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final FlatFileItemReader<TransactionMetadata> transactionMetadataFlatFileItemReader;
    private final TransactionMetaDataProcessor transactionMetaDataProcessor;
    private final JdbcBatchItemWriter<TransactionMetadata> transactionMetadataJdbcBatchItemWriter;

    @Bean
    public Step transactionMetadataStep() {

        return new StepBuilder("transactionMetaDataStep", jobRepository)
                .<TransactionMetadata, TransactionMetadata>chunk(100, transactionManager)
                .reader(transactionMetadataFlatFileItemReader)
                .processor(transactionMetaDataProcessor)
                .writer(transactionMetadataJdbcBatchItemWriter)
                .build();
    }

    @Bean
    public Job transactionImportJob() {

        return new JobBuilder(
                "transactionImportJob",
                jobRepository)
                .start(transactionMetadataStep())
                .build();
    }
}
