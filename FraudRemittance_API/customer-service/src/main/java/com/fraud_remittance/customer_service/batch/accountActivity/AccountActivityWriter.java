package com.fraud_remittance.customer_service.batch.accountActivity;

import com.fraud_remittance.customer_service.entity.AccountActivity;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AccountActivityWriter {
    @Bean
    public JdbcBatchItemWriter<AccountActivity> accountActivityItemWriter(
            DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<AccountActivity>()
                .dataSource(dataSource)
                .sql("""
                INSERT INTO account_activity
                (customer_id, account_balance, last_login)
                VALUES
                (:customerId, :accountBalance, :lastLogin)
                """)
                .beanMapped()
                .build();
    }
}
