package org.bandit.transactioncacheapi.services;

import org.bandit.transactioncacheapi.models.Transaction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.UUID;

@EnableScheduling
@Service
public class TransactionGeneratorService {
    /*
    Generates 50 transactions an hour from 12 pm to 8 pm UTC
     */
    @Scheduled(cron = "0 0 12-20 * * ?", zone = "UTC")
    public void generateDayTransactions() {
        generateTransaction(50);
    }
    /*
    Generates 5 transactions an hour from 8 pm to 12 pm UTC
     */
    @Scheduled(cron = "0 0 20-23,0-12 * * ?", zone = "UTC")
    public void generateNightTransactions() {
        generateTransaction(5);
    }

    public void generateTransaction(int amount) {
        for (int i = 0; i < amount; i++) {
            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(), // transactionId
                    "userId",
                    "transactionType",
                    generateRandomTransactionDate(),
                    "PENDING",
                    generateRandomTransactionAmount(),
                    generateRandomTransactionCurrency()
            );
        }
    }

    public String generateRandomTransactionCurrency() {
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD"};
        int randomIndex = (int) (Math.random() * currencies.length);
        return currencies[randomIndex];
    }
    public double generateRandomTransactionAmount() {
        return Math.random() * 1000;
    }
    public String generateRandomTransactionDate() {
        int year = (int) (Math.random() * (2023 - 2020 + 1)) + 2020;
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
