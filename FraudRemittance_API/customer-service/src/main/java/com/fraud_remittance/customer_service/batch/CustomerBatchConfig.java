package com.fraud_remittance.customer_service.batch;

import com.fraud_remittance.customer_service.entity.Customer;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.FileNotFoundException;

@Configuration
@RequiredArgsConstructor
public class CustomerBatchConfig {

    private final CustomerReader customerReader;
    private final CustomerProcessor customerProcessor;
    private final CustomerWriter customerWriter;

    @Bean
    public Step customerStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager) throws FileNotFoundException {

        return new StepBuilder("customerStep", jobRepository)
                .<Customer, Customer>chunk(
                        10,
                        transactionManager)
                .reader(customerReader.customerItemReader())
                .processor(customerProcessor)
                .writer(customerWriter.customerItemWriter())
                .build();
    }

    @Bean
    public Job customerImportJob(
            JobRepository jobRepository,
            Step customerStep) {

        return new JobBuilder("customerImportJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(customerStep)
                .build();
    }
}
