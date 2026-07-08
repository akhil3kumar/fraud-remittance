package com.fraud_remittance.customer_service.service.impl;

import com.fraud_remittance.customer_service.dto.CustomerMapper;
import com.fraud_remittance.customer_service.entity.Customer;
import com.fraud_remittance.customer_service.repository.CustomerRepostitory;
import com.fraud_remittance.customer_service.service.CustomerService;
import dto.customer.CustomerResponse;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceIml implements CustomerService {

    private final CustomerRepostitory customerRepostitory;
    private final CustomerMapper customerMapper;

    @Override
    public Page<CustomerResponse> getCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepostitory
                .findAll(pageable);

        return customers.map(
                customerMapper::toCustomerResponse);
    }


    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        Customer customer = customerRepostitory.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer with id " + customerId + " not found")
        );

       return customerMapper.toCustomerResponse(customer);

    }

}
