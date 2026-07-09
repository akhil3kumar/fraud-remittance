package com.fraud_remittance.customer_service.controller;

import com.fraud_remittance.customer_service.service.AccountActivityService;
import com.fraud_remittance.customer_service.service.CustomerService;
import dto.customer.AccountActivityResponse;
import dto.customer.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class AccountActivityController {

    private final AccountActivityService accountActivityService;

    @GetMapping("/account-activities")
    public ResponseEntity<Page<AccountActivityResponse>> getAccountActivity(
            Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(accountActivityService.getAccountActivity(pageable));
    }

    @GetMapping("/customer/{id}/account-activity")
    public ResponseEntity<CustomerResponse> getAccountActivityByCustomerId(@PathVariable(name = "id") Long customerId){
        return ResponseEntity.status(HttpStatus.OK).body(accountActivityService.getAccountActivityByCustomerId(customerId));
    }

}
