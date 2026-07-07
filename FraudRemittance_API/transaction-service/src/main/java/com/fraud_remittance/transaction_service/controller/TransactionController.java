package com.fraud_remittance.transaction_service.controller;

import com.fraud_remittance.transaction_service.service.TransactionService;
import dto.transaction.TransactionRequest;
import dto.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse>
    createTransaction(
            @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(
                transactionService.createTransaction(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse>
    getTransaction(@PathVariable Long id) {

        return ResponseEntity.ok(
                transactionService.getTransaction(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>>
    getAllTransactions() {

        return ResponseEntity.ok(
                transactionService.getAllTransactions()
        );
    }
}
