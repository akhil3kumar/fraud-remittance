package com.fraud_remittance.customer_service.controller;

import com.fraud_remittance.customer_service.entity.AccountActivity;
import com.fraud_remittance.customer_service.service.CustomerService;
import dto.customer.AccountActivityResponse;
import dto.customer.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getCustomers(
            Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(name = "id") Long customerId){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(customerId));
    }

}
