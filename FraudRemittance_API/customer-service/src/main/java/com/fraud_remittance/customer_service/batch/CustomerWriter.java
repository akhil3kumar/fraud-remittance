package com.fraud_remittance.customer_service.batch;

import com.fraud_remittance.customer_service.entity.Customer;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerWriter{

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaItemWriter<Customer> customerItemWriter() {
        JpaItemWriter<Customer> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}
