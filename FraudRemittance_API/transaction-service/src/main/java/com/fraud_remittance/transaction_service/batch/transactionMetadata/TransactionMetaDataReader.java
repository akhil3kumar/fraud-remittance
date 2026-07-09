package com.fraud_remittance.transaction_service.batch.transactionMetadata;

import com.fraud_remittance.transaction_service.entity.Transaction;
import com.fraud_remittance.transaction_service.entity.TransactionMetadata;
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
public class TransactionMetaDataReader {
    @Value("${batch.transactionMetadata.file-path}")
    private Resource inputPath;

    @Bean
    public FlatFileItemReader<TransactionMetadata> transactionMetadataItemReader() throws FileNotFoundException {
        System.out.println("PATH = " + inputPath);
        FlatFileItemReader<TransactionMetadata> reader = new FlatFileItemReader<>();
        reader.setResource(inputPath);
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setNames("transactionId", "timestamp", "amount");
        BeanWrapperFieldSetMapper<TransactionMetadata> mapper =
                new BeanWrapperFieldSetMapper<>();

        mapper.setTargetType(TransactionMetadata.class);

        DefaultLineMapper<TransactionMetadata> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }
}
