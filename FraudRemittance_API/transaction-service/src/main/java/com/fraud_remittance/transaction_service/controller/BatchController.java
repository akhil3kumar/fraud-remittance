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
public class BatchController {

    private final JobLauncher jobLauncher;

    private final Job transactionImportJob;

    private final Job transactionMetadataJob;

    public  BatchController(JobLauncher jobLauncher,
                            @Qualifier("transactionImportJob") Job transactionImportJob,
                            @Qualifier("transactionMetadataImportJob") Job transactionMetadataImportJob){

        this.jobLauncher = jobLauncher;
        this.transactionImportJob = transactionImportJob;
        this.transactionMetadataJob = transactionMetadataImportJob;

    }



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

    @PostMapping("/transaction-metadata")
    public String importTransactionMetaData() throws Exception {

        JobParameters params =
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();

        jobLauncher.run(transactionMetadataJob, params);

        return "TransactionMetaData import started";
    }

    @PostMapping("/all")
    public String importAll() throws Exception {
        JobParameters transactionParams = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(transactionImportJob, transactionParams);

        JobParameters transactionMetaDataParams = new JobParametersBuilder()
                .addLong("time", System.nanoTime())
                .toJobParameters();

        jobLauncher.run(transactionMetadataJob, transactionMetaDataParams);

        return "All batch jobs started";
    }
}
