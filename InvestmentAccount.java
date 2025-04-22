package MidtermrepresentativeSerrano2;

/**
 * sets an inheritance to the parent class
 */

public class InvestmentAccount extends BankAccounts {
    private double minimumBalance;
    private double interest;

    /**
     * sets the minimum balance to 0.0
     * sets the interest to 0.0
     */
    public InvestmentAccount() {
        super();
        this.minimumBalance = 0.0;
        this.interest = 0.0;
    }

    /**
     * shows the investment account information (variable)
     * @param accountNo this post object initializes accountNo.
     * @param accountName this post object initializes accountName
     * @param minimumBalance this post object initializes minimumBalance
     * @param interest this post object initializes interest
     */


    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(String.valueOf(accountNo), accountName);
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }

    /**
     * Gets the minimum balance
     * @return this post object returns the minimum balance
     */

    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * gets the interest of the account
     * @return this object returns the interest
     */

    public double getInterest() {
        return interest;
    }

    /**
     * Determines if the added investment amount is invalid or valid
     * @param amount this post object adds the invest amount
     */

    public void addInvestment(double amount) {
        if (amount > 0) {
            deposit(amount);
        } else {
            System.out.println("Invalid investment amount.");
        }
    }

    /**
     * inquires investment value
     */

    public void inquireInvestmentValue() {
        double currentValue = inquireBalance() * (1 + interest);
        System.out.println("Current investment value: " + currentValue);
    }

    /**
     * closes the account
     */

    public void closeAccount() {
        if (getStatus().equals("Closed")) {
            System.out.println("Account is already closed.");
            return;
        }

        inquireInvestmentValue();
        withdraw(inquireBalance());
        super.closeAccount();
        System.out.println("Investment account closed. Remaining balance withdrawn.");
    }

    /**
     * overrides the method from bank accounts
     * @param amount this post object is the amount to withdraw
     */

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed in MidProjRepresentativeSerrano.InvestmentAccount.");
    }

    /**
     * overrides the method from bank account
     *
     * @param accounts this post object where the amount will go.
     * @param acctno this post object is where the account number will go.
     * @param amount this post object determines how much will be transfered
     */

    @Override
    public void transferMoney(BankAccounts[] accounts, int acctno, double amount) {
        System.out.println("Transfers are not allowed in MidProjRepresentativeSerrano.InvestmentAccount.");
    }
}