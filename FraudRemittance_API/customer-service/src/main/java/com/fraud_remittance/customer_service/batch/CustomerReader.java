package com.fraud_remittance.customer_service.batch;

import com.fraud_remittance.customer_service.entity.Customer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Configuration
public class CustomerReader {


    @Value("${batch.customer.file-path}")
    private Resource inputPath;

    @Bean
    public FlatFileItemReader<Customer> customerItemReader() throws FileNotFoundException {
        System.out.println("PATH = " + inputPath);
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setResource(inputPath);
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setNames("customerId", "name", "age", "address");
        BeanWrapperFieldSetMapper<Customer> mapper =
                new BeanWrapperFieldSetMapper<>();

        mapper.setTargetType(Customer.class);

        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }
}
