package org.bandit.transactioncacheapi.repositories;

import org.bandit.transactioncacheapi.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
