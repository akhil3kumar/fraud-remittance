package com.fraud_remittance.transaction_service.service;


import dto.transaction.TransactionRequest;
import dto.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse createTransaction(
            TransactionRequest request);

    TransactionResponse getTransaction(
            Long transactionId);

    List<TransactionResponse> getAllTransactions();
}
