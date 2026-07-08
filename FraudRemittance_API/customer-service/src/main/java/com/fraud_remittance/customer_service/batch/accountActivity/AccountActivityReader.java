package com.fraud_remittance.customer_service.batch.accountActivity;

import com.fraud_remittance.customer_service.entity.AccountActivity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
@Configuration
public class AccountActivityReader {
    @Value("${batch.account-activity.file-path}")
    private Resource inputPath;

    @Bean
    public FlatFileItemReader<AccountActivity> accountActivityItemReader() throws FileNotFoundException {
        System.out.println("PATH = " + inputPath);
        FlatFileItemReader<AccountActivity> reader = new FlatFileItemReader<>();
        reader.setResource(inputPath);
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("customerId", "accountBalance", "lastLogin");
        BeanWrapperFieldSetMapper<AccountActivity> mapper =
                new BeanWrapperFieldSetMapper<>();

        mapper.setTargetType(AccountActivity.class);

        DefaultLineMapper<AccountActivity> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }
}
