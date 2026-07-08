package com.fraud_remittance.customer_service.batch.customer;

import com.fraud_remittance.customer_service.entity.Customer;
import com.fraud_remittance.customer_service.repository.CustomerRepostitory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    private final CustomerRepostitory customerRepostitory;
    @Override
    public Customer process(Customer customer) throws Exception {

        return customer;
    }
}
