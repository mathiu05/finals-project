package FinProjSerrano;

import java.util.ArrayList;
import java.util.List;

public class BankAccounts {
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;
    private List<Transaction> transactionHistory;

    public BankAccounts(String accountNo, String accountName) {
        this.accountNo = Integer.parseInt(accountNo);
        this.accountName = accountName;
        this.balance = 0.0;
        this.status = "active";
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getStatus() {
        return status;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        logTransaction("Deposit", amount);
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException, InvalidAmountException {
        if (status.equals("closed")) {
            throw new AccountClosedException("Account is closed.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        balance -= amount;
        logTransaction("Withdrawal", amount);
        System.out.println("Withdrawal successful. New balance: " + balance);
    }

    public double inquireBalance() {
        return balance;
    }

    public void closeAccount() {
        status = "closed";
        System.out.println("Account " + accountNo + " closed successfully.");
    }

    protected void logTransaction(String type, double amount) {
        transactionHistory.add(new Transaction(type, amount, accountNo));
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
