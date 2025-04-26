package FinProjSerrano;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionType;
    private double amount;
    private int accountNumber;
    private LocalDateTime timestamp;

    public Transaction(String transactionType, double amount, int accountNumber) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
