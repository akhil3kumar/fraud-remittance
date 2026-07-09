package com.fraud_remittance.transaction_service.controller;

import com.fraud_remittance.transaction_service.service.TransactionMetadataService;
import com.fraud_remittance.transaction_service.service.TransactionService;
import dto.transaction.TransactionMetadataResponse;
import dto.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionMetadataController {

    private final TransactionMetadataService transactionMetadataService;

    @GetMapping("/transaction-metadata")
    public ResponseEntity<Page<TransactionMetadataResponse>> getTransactions(Pageable pageable) {

        return ResponseEntity.ok(
                transactionMetadataService.getTransactionMetadata(pageable)
        );
    }

    @GetMapping("transaction-metadata/{transactionId}")
    public ResponseEntity<Page<TransactionMetadataResponse>> getTransactionsMetadataByCustomer(
            @PathVariable Long transactionId, Pageable pageable) {

        return ResponseEntity.ok(
                transactionMetadataService.getTransactionsMetadataByTransaction(
                        transactionId,
                        pageable
                )
        );
    }
}
