package com.fraud_remittance.customer_service.dto;

import com.fraud_remittance.customer_service.entity.Customer;
import dto.customer.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerResponse toCustomerResponse(Customer  customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .age(customer.getAge())
                .address(customer.getAddress())
                .build();
    };
}
