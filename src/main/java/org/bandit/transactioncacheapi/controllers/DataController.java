package org.bandit.transactioncacheapi.controllers;

import org.bandit.transactioncacheapi.models.Transaction;
import org.bandit.transactioncacheapi.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
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

        List<Transaction> transactions = transactionService.getTransactions(
                minAmount, maxAmount, transactionType, transactionStatus);
        if (transactions.isEmpty()) {
            logger.info("No transactions found with the given criteria.");
            return ResponseEntity.noContent().build();
        }
        logger.info("Transactions found: {}", transactions.size());
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/data/mostRecentTransactions")
    public ResponseEntity<List<Transaction>> getMostRecentTransactions(
            @RequestParam int mostRecentTransactions
    ) {
        List<Transaction> transactions = transactionService.getMostRecentTransactions(mostRecentTransactions);
        if (transactions.isEmpty()) {
            logger.info("No recent transactions found.");
            return ResponseEntity.noContent().build();
        }
        logger.info("Most recent transactions found: {}", transactions.size());
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/data/health")
    public ResponseEntity<String> healthCheck() {
        logger.info("Health check endpoint called.");
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
        logger.info("Transaction generated: {}", transaction);
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
