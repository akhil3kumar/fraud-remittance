package com.fraud_remittance.customer_service.batch.customer;

import com.fraud_remittance.customer_service.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class CustomerWriter{

    private final DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<Customer> customerItemWriter() {
        JdbcBatchItemWriter<Customer> writer =
                new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);

        writer.setSql("""
        INSERT INTO customer
        (customer_id, name, age, address)
        VALUES
        (:customerId, :name, :age, :address)
        ON CONFLICT (customer_id)
        DO NOTHING
        """);

        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setAssertUpdates(false);
        writer.afterPropertiesSet();

        return writer;
    }
}
