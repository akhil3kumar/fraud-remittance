package com.fraud_remittance.customer_service.batch.accountActivity;

import com.fraud_remittance.customer_service.entity.AccountActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.FileNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AccountActivityBatchConfig {

    private final AccountActivityProcessor accountActivityProcessor;

    @Bean
    public Step accountActivityStep(JobRepository jobRepository,
                                    PlatformTransactionManager transactionManager,
                                    FlatFileItemReader<AccountActivity> accountActivityItemReader,
                                    JdbcBatchItemWriter<AccountActivity> accountActivityItemWriter) throws FileNotFoundException {

        return new StepBuilder("accountActivity-step", jobRepository)
                .<AccountActivity, AccountActivity>chunk(10, transactionManager)
                .reader(accountActivityItemReader)
                .processor(accountActivityProcessor)
                .writer(accountActivityItemWriter)
                .build();
    }

    @Bean
    public Job accountActivityImportJob(
            JobRepository jobRepository,
            Step accountActivityStep) {

        return new JobBuilder("accountActivityImportJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(accountActivityStep)
                .build();
    }
}
