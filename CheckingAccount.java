package MidtermrepresentativeSerrano2;

/**
 * checking Account class
 */
public class CheckingAccount extends BankAccounts {
    private double minimumBalance;

    /**
     * checks the minimum balance
     */
    public CheckingAccount() {
        super();
        this.minimumBalance = 0.0;
    }

    /**
     * checks the variables
     * @param accountNo this post object checks the account No
     * @param accountName this post object checks the accountName
     * @param minimumBalance this post object checks minimum balance
     */

    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(String.valueOf(accountNo), accountName);
        this.minimumBalance = minimumBalance;
    }

    /**
     * gets the minimum balance
     * @return returns the minimum balance
     */

    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * checks the amount
     * @param amount this object checks the cash
     */

    public void encashCheck(double amount) {
        try {
            if (!getStatus().equals("active")) {
                throw new IllegalStateException("Account is closed. Cannot encash check.");
            }

            if (amount > inquireBalance() - minimumBalance) {
                throw new IllegalArgumentException("Insufficient balance. Cannot encash check.");
            }

            withdraw(amount);
            System.out.println("Check encashed successfully. New balance: " + inquireBalance());
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}