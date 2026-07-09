package com.fraud_remittance.customer_service.controller;

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
@RequestMapping("/customers/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job customerImportJob;
    private final Job accountActivityImportJob;

    public BatchController(
            JobLauncher jobLauncher,
            @Qualifier("customerImportJob") Job customerImportJob,
            @Qualifier("accountActivityImportJob") Job accountActivityImportJob) {

        this.jobLauncher = jobLauncher;
        this.customerImportJob = customerImportJob;
        this.accountActivityImportJob = accountActivityImportJob;
    }

    @PostMapping("/customers")
    public String importCustomers() throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(customerImportJob, params);

        return "Customer import started";
    }

    @PostMapping("/account-activities")
    public String importAccountActivities() throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(accountActivityImportJob, params);

        return "Account Activity import started";
    }

    @PostMapping("/all")
    public String importAll() throws Exception {

        JobParameters customerParams = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(customerImportJob, customerParams);

        JobParameters accountParams = new JobParametersBuilder()
                .addLong("time", System.nanoTime())
                .toJobParameters();

        jobLauncher.run(accountActivityImportJob, accountParams);

        return "All imports started";
    }

}
