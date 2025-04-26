package FinProjSerrano;

public class InvestmentAccount extends BankAccounts {
    private double interestRate;

    public InvestmentAccount(String accountNo, String accountName, double interestRate) {
        super(accountNo, accountName);
        this.interestRate = interestRate;
    }

    public void computeMonthlyInterest() {
        double interest = inquireBalance() * (interestRate / 100.0);
        try {
            deposit(interest);
            System.out.println("Monthly interest added: " + interest);
        } catch (InvalidAmountException e) {
            System.out.println("Error computing interest: " + e.getMessage());
        }
    }
}
