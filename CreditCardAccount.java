package FinProjSerrano;

public class CreditCardAccount extends BankAccounts {
    private double creditLimit;
    private double charges;

    public CreditCardAccount(String accountNo, String accountName, double creditLimit) {
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = 0.0;
    }

    public void chargeToCard(double amount) throws InsufficientFundsException {
        if (charges + amount > creditLimit) {
            throw new InsufficientFundsException("Charge exceeds credit limit.");
        }
        charges += amount;
        logTransaction("Card Charge", amount);
        System.out.println("Charge successful. Current charges: " + charges);
    }

    public void payCard(double amount) {
        charges -= amount;
        if (charges < 0) {
            charges = 0;
        }
        logTransaction("Card Payment", amount);
        System.out.println("Payment successful. Remaining charges: " + charges);
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getCharges() {
        return charges;
    }
}
