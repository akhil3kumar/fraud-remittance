package com.fraud_remittance.customer_service.batch.accountActivity;

import com.fraud_remittance.customer_service.entity.AccountActivity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountActivityProcessor implements ItemProcessor<AccountActivity, AccountActivity> {
    @Override
    public AccountActivity process(AccountActivity item) throws Exception {
        return item;
    }
}
