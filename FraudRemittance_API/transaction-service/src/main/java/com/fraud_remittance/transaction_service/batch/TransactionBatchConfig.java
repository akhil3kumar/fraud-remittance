package com.fraud_remittance.transaction_service.batch;

import com.fraud_remittance.transaction_service.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class TransactionBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final FlatFileItemReader<Transaction> transactionItemReader;
    private final TransactionProcessor transactionProcessor;
    private final JdbcBatchItemWriter<Transaction> transactionItemWriter;


    @Bean
    public Step transactionStep() {

        return new StepBuilder("transactionStep", jobRepository)
                .<Transaction, Transaction>chunk(100, transactionManager)
                .reader(transactionItemReader)
                .processor(transactionProcessor)
                .writer(transactionItemWriter)
                .build();
    }

    @Bean
    public Job transactionImportJob() {

        return new JobBuilder(
                "transactionImportJob",
                jobRepository)
                .start(transactionStep())
                .build();
    }

}
