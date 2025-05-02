package org.bandit.transactioncacheapi.services;

import org.bandit.transactioncacheapi.models.Transaction;
import org.bandit.transactioncacheapi.repositories.TransactionRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class TransactionGeneratorService {
    @Scheduled(cron = "0 0 * * * ?", zone = "UTC")
    public void generateTransactions() {
        Transaction transaction = new Transaction(
                "transactionId",
                "userId",
                "itemId",
                "transactionType",
                "transactionDate",
                "transactionStatus",
                "transactionAmount",
                "transactionCurrency"
        );

    }
}
