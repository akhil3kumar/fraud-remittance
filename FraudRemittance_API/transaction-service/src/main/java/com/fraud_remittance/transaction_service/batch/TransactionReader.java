package com.fraud_remittance.transaction_service.batch;

import com.fraud_remittance.transaction_service.entity.Transaction;
import dto.transaction.TransactionResponse;
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
public class TransactionReader {
    @Value("${batch.transaction.file-path}")
    private Resource inputPath;

    @Bean
    public FlatFileItemReader<Transaction> transactionItemReader() throws FileNotFoundException {
        System.out.println("PATH = " + inputPath);
        FlatFileItemReader<Transaction> reader = new FlatFileItemReader<>();
        reader.setResource(inputPath);
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setNames("transactionId", "amount", "customerId");
        BeanWrapperFieldSetMapper<Transaction> mapper =
                new BeanWrapperFieldSetMapper<>();

        mapper.setTargetType(Transaction.class);

        DefaultLineMapper<Transaction> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }

}
