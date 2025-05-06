package org.bandit.transactioncacheapi.controllers;

import org.bandit.transactioncacheapi.models.Transaction;
import org.bandit.transactioncacheapi.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class DataController {
    private final TransactionService transactionService;

    public DataController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/data")
    public ResponseEntity<List<Transaction>> getTransactions(
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            @RequestParam(required = false) String transactionType,
            @RequestParam(required = false) String transactionStatus
            ) {
        // Get data from cache here or if a cache miss, get from db thewn save to cache
        // TEMPORARY
        return ResponseEntity.ok(List.of(
                new Transaction("1", "user1", "purchase", "2023-10-01", "completed", 100.0, "USD"),
                new Transaction("2", "user2", "refund", "2023-10-02", "pending", 50.0, "USD")
        ));
    }

    @GetMapping("/data/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/generateData")
    public ResponseEntity<String> generateData(
            @RequestParam String transactionId,
            @RequestParam String userId,
            @RequestParam String transactionType,
            @RequestParam String transactionStatus,
            @RequestParam String transactionDate,
            @RequestParam String transactionCurrency,
            @RequestParam double transactionAmount
    ) {
        // Generate data here
        // TEMPORARY
        Transaction transaction = new Transaction(
                transactionId,
                userId,
                transactionType,
                transactionDate,
                transactionStatus,
                transactionAmount,
                transactionCurrency
        );
        transactionService.saveTransaction(transaction);
        // Save to cache here later
        return ResponseEntity.ok("Data generated" +
                " transactionId: " + transactionId +
                " userId: " + userId +
                " transactionType: " + transactionType +
                " transactionStatus: " + transactionStatus +
                " transactionDate: " + transactionDate +
                " transactionCurrency: " + transactionCurrency +
                " transactionAmount: " + transactionAmount);
    }
}
