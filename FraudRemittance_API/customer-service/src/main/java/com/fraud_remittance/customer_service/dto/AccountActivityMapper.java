package com.fraud_remittance.customer_service.dto;

import com.fraud_remittance.customer_service.entity.AccountActivity;
import dto.customer.AccountActivityResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountActivityMapper {

    public AccountActivityResponse toAccountActivityResponse(AccountActivity accountActivity) {
        return AccountActivityResponse.builder()
                .accountActivityId(accountActivity.getId())
                .customerId(accountActivity.getCustomerId())
                .accountBalance(accountActivity.getAccountBalance())
                .lastLogin(accountActivity.getLastLogin())
                .build();
    }
}
