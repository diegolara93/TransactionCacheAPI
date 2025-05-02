package org.bandit.transactioncacheapi.services;

import org.bandit.transactioncacheapi.models.Transaction;
import org.bandit.transactioncacheapi.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
