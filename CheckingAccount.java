package FinProjSerrano;

public class CheckingAccount extends BankAccounts {
    private double minimumBalance;

    public CheckingAccount(String accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException, InvalidAmountException {
        if (inquireBalance() - amount < minimumBalance) {
            throw new InsufficientFundsException("Withdrawal would breach minimum balance.");
        }
        super.withdraw(amount);
    }
}
