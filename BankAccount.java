package MidtermrepresentativeSerrano2;

public class BankAccounts {
    private int accountNo;
    private String accountName;
    private double balance;
    private String status;

    public BankAccounts() {
        this.status = "active";
        this.balance = 0.0;
    }

    /**
     * This sets the variables to its corresponding  knowledge.
     *
     * @param accountNo   Post object to initialize AccountNo
     * @param accountName Post object to initialize AccountName
     */


    public BankAccounts(String accountNo, String accountName) {
        this.status = "active";
        this.balance = 0.0;
        this.accountName = accountName;
    }

    /**
     * This gets the value of AccountNo
     *
     * @return this returns the AccountNo
     */
    public int getAccountNo() {
        return accountNo;
    }

    /**
     * This gets the AccountName's value
     *
     * @return this object returns the accountName
     */

    public String getAccountName() {
        return accountName;
    }

    /**
     * This gets the getStatus value whether the account its active of inactive
     *
     * @return this returns the status
     */

    public String getStatus() {
        return status;
    }

    /**
     * this set's the accountNo of the user
     *
     * @param accountNo this post object is to set the AccountNo
     */

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;

    }

    /**
     * Sets the account name of the user or the user sets the account name
     *
     * @param accountName This post object is to set the accountName
     */

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * it overrides every method
     *
     * @return returns every Strings and integers
     */

    @Override
    public String toString() {
        return "Account Number: " + accountNo + ", Account Name: " + accountName + ", Balanced: " + balance + "Status: " + status;
    }

    /**
     * deposit's the user's amount and if amount is not lower than the limit amount then the deposit is successful
     * If the amount is less than the limit it will print invalid amount
     *
     * @param amount this post object determines the deposit
     */

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New Balance: " + balance);
        } else {
            System.out.println(" Sorry! Invalid Amount.");
        }
    }

    /**
     * this determines if the user can withdraw or not
     *
     * @param amount this post object is the amount to withdraw
     */

    public void withdraw(double amount) {
        if (status.equals("closed")) {
            System.out.println("Account is closed. Balance: " + balance + " is available for withdrawal."); dito pre need lang natin dagdagan nang parang wait 
            since account is closed reconfirm how much you want to withdraw 
        } else if (amount > balance) {
            System.out.println("insufficient balance. Withdrawal cancelled");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    public double inquireBalance() {
        return balance;
    }

    /**
     * determines if it can be transferred or not
     *
     * @param accounts this post object where the amount will go.
     * @param acctNo   this post object is where the account number will go.
     * @param amount   this post object determines how much will be transfered
     */

    public void transferMoney(BankAccounts[] accounts, int acctNo, double amount) {
        if (status.equals("active")) {
            if (amount > 0 && balance >= amount) {
                boolean accountFound = false;
                for (BankAccounts account : accounts) {
                    if (account != null && account.getAccountNo() == acctNo && account.getStatus().equals("active")) {
                        this.balance -= amount;
                        account.deposit(amount);
                        System.out.println("Transfer of " + amount + " to account " + acctNo + " successful.");
                        accountFound = true;
                        break;
                    }
                }
                if (!accountFound) {
                    System.out.println("Account " + acctNo + " not found or not active.");
                }
            } else if (amount <= 0) {
                System.out.println("Invalid transfer amount");
            } else {
                System.out.println("Insufficient balance for transfer.");
            }
        } else {
            System.out.println("Account is closed");
        }
    }

    /**
     * this functions as the closing where the user can close the account
     */

    public void closeAccount() {
        this.status = "closed";
        System.out.println("Account " + accountNo + " closed. Balance: " + balance + " is available for withdrawal."); 
    }
}
