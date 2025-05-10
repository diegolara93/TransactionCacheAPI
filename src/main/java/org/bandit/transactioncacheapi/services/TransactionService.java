package org.bandit.transactioncacheapi.services;

import org.bandit.transactioncacheapi.models.Transaction;
import org.bandit.transactioncacheapi.repositories.TransactionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @CacheEvict(value = "transactions", allEntries = true)
    public void evictTransactionsCache() {
        // clear the transactions cache
    }
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        evictTransactionsCache();
    }

    @Cacheable(value = "transactions", key = "{#minAmount, #maxAmount, #transactionType, #transactionStatus}")
    public List<Transaction> getTransactions(
            BigDecimal minAmount,
            BigDecimal maxAmount,
            String transactionType,
            String transactionStatus) {

        List<Transaction> allTransactions = StreamSupport
                .stream(transactionRepository.findAll().spliterator(), false)
                .toList();

        List<Transaction> filteredTransactions = allTransactions.stream()
                .filter(t -> minAmount == null || BigDecimal.valueOf(t.getTransactionAmount()).compareTo(minAmount) >= 0)
                .filter(t -> maxAmount == null || BigDecimal.valueOf(t.getTransactionAmount()).compareTo(maxAmount) <= 0)
                .filter(t -> transactionType == null || t.getTransactionType().equals(transactionType))
                .filter(t -> transactionStatus == null || t.getTransactionStatus().equals(transactionStatus))
                .collect(Collectors.toList());

        return filteredTransactions;
    }
    /*
    Refreshes the cache every hour
     */
    @Scheduled(fixedRate = 3600000)
    @CacheEvict(value = "transactions", allEntries = true)
    public void refreshCache() {
        System.out.println("Refreshing transactions cache at " + new java.util.Date());
    }
}
