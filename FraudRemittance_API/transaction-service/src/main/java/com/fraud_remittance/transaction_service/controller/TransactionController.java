package com.fraud_remittance.transaction_service.controller;

import com.fraud_remittance.transaction_service.service.TransactionService;
import dto.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> getTransactions(Pageable pageable) {

        return ResponseEntity.ok(
                transactionService.getTransactions(pageable)
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<TransactionResponse>> getTransactionsByCustomer(
            @PathVariable Long customerId, Pageable pageable) {

        return ResponseEntity.ok(
                transactionService.getTransactionsByCustomer(
                        customerId,
                        pageable
                )
        );
    }
}
