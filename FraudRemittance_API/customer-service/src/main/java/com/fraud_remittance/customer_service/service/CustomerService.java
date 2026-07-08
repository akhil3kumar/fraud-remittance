package com.fraud_remittance.customer_service.service;

import dto.customer.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponse getCustomerById(Long customerId);
    Page<CustomerResponse> getCustomers(Pageable pageable);
}
