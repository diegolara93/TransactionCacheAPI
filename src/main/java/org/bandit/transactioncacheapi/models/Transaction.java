package org.bandit.transactioncacheapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String userId;
    private String itemId;
    private String transactionType;
    private String transactionDate;
    private String transactionStatus;
    private String transactionAmount;
    private String transactionCurrency;

    public Transaction(
            String transactionId,
            String userId,
            String itemId,
            String transactionType,
            String transactionDate,
            String transactionStatus,
            String transactionAmount,
            String transactionCurrency
    ) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.itemId = itemId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
    }
    public Transaction() { }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public String getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public String getTransactionCurrency() {
        return transactionCurrency;
    }
    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }
}
