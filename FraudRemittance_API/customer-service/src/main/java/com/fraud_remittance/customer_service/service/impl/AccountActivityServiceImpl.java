package com.fraud_remittance.customer_service.service.impl;

import com.fraud_remittance.customer_service.dto.AccountActivityMapper;
import com.fraud_remittance.customer_service.entity.AccountActivity;
import com.fraud_remittance.customer_service.repository.AccountActivityRepository;
import com.fraud_remittance.customer_service.service.AccountActivityService;
import dto.customer.AccountActivityResponse;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountActivityServiceImpl implements AccountActivityService {

    private final AccountActivityRepository accountActivityRepository;
    private final AccountActivityMapper accountActivityMapper;
    @Override
    public Page<AccountActivityResponse> getAccountActivity(Pageable pageable) {
        Page<AccountActivity> accountActivities = accountActivityRepository
                .findAll(pageable);

        return accountActivities.map(
                accountActivityMapper::toAccountActivityResponse);
    }

    @Override
    public AccountActivityResponse getAccountActivityByCustomerId(Long customerId) {
        AccountActivity activity = accountActivityRepository
                .findByCustomerId(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account activity not found for customer id: "
                                        + customerId));

        return accountActivityMapper.toAccountActivityResponse(activity);

    }
}
