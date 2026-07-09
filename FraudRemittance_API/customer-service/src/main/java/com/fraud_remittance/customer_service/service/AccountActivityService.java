package com.fraud_remittance.customer_service.service;

import dto.customer.AccountActivityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountActivityService {

    Page<AccountActivityResponse> getAccountActivity(Pageable pageable);

    AccountActivityResponse getAccountActivityByCustomerId(Long customerId);
}
