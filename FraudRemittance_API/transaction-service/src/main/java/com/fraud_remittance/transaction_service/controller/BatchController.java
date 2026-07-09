package com.fraud_remittance.transaction_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/batch")
@RequiredArgsConstructor
public class BatchController {

    private final JobLauncher jobLauncher;

    @Qualifier("transactionImportJob")
    private final Job transactionImportJob;

    @PostMapping("/transactions")
    public String importTransactions() throws Exception {

        JobParameters params =
                new JobParametersBuilder()
                        .addLong(
                                "time",
                                System.currentTimeMillis())
                        .toJobParameters();

        jobLauncher.run(transactionImportJob, params);

        return "Transaction import started";
    }
}
