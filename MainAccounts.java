package MidtermrepresentativeSerrano2;

import java.util.Scanner;

public class MainAccounts {
    private static BankAccounts[] bankAccounts = new BankAccounts[5];
    private static CheckingAccount[] checkingAccounts = new CheckingAccount[5];
    private static CreditCardAccount[] creditCardAccounts = new CreditCardAccount[5];
    private static InvestmentAccount[] investmentAccounts = new InvestmentAccount[5];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Bank Account Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Balance Inquiry");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Money");
            System.out.println("6. Close Account");
            System.out.println("7. Encash Check (Checking Account)");
            System.out.println("8. Charge to Card (Credit Card Account)");
            System.out.println("9. Pay Card (Credit Card Account)");
            System.out.println("10. Cash Advance (Credit Card Account)");
            System.out.println("11. Inquire Available Credit (Credit Card Account)");
            System.out.println("12. Add Investment (Investment Account)");
            System.out.println("13. Inquire Investment Value (Investment Account)");
            System.out.println("14. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    inquireBalance();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    transferMoney();
                    break;
                case 6:
                    closeAccount();
                    break;
                case 7:
                    encashCheck();
                    break;
                case 8:
                    chargeToCard();
                    break;
                case 9:
                    payCard();
                    break;
                case 10:
                    cashAdvance();
                    break;
                case 11:
                    inquireAvailableCredit();
                    break;
                case 12:
                    addInvestment();
                    break;
                case 13:
                    inquireInvestmentValue();
                    break;
                case 14:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void createAccount() {
        System.out.println("Choose account type: 1. BankAccount 2. Checking 3. CreditCard 4. Investment");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Account Number (9 digits): ");
        String accountNo = scanner.nextLine();
        System.out.print("Enter Account Name: ");
        String accountName = scanner.nextLine();

        switch (type) {
            case 1:
                addBankAccount(accountNo, accountName);
                break;
            case 2:
                addCheckingAccount(accountNo, accountName);
                break;
            case 3:
                addCreditCardAccount(accountNo, accountName);
                break;
            case 4:
                addInvestmentAccount(accountNo, accountName);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addBankAccount(String accountNo, String accountName) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i] == null) {
                try {
                    bankAccounts[i] = new BankAccounts(accountNo, accountName);
                    System.out.println("Bank Account created successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Bank account storage is full.");
    }

    private static void addCheckingAccount(String accountNo, String accountName) {
        System.out.print("Enter Minimum Balance: ");
        double minBalance = scanner.nextDouble();
        for (int i = 0; i < checkingAccounts.length; i++) {
            if (checkingAccounts[i] == null) {
                checkingAccounts[i] = new CheckingAccount(Integer.parseInt(accountNo), accountName, minBalance);
                System.out.println("Checking Account created successfully.");
                return;
            }
        }
        System.out.println("Checking account storage is full.");
    }

    private static void addCreditCardAccount(String accountNo, String accountName) {
        System.out.print("Enter Credit Limit: ");
        double creditLimit = scanner.nextDouble();
        for (int i = 0; i < creditCardAccounts.length; i++) {
            if (creditCardAccounts[i] == null) {
                creditCardAccounts[i] = new CreditCardAccount(Integer.parseInt(accountNo), accountName, creditLimit, 0);
                System.out.println("Credit Card Account created successfully.");
                return;
            }
        }
        System.out.println("Credit card account storage is full.");
    }

    private static void addInvestmentAccount(String accountNo, String accountName) {
        System.out.print("Enter Minimum Balance: ");
        double minBalance = scanner.nextDouble();
        System.out.print("Enter Interest Rate: ");
        double interest = scanner.nextDouble();
        for (int i = 0; i < investmentAccounts.length; i++) {
            if (investmentAccounts[i] == null) {
                investmentAccounts[i] = new InvestmentAccount(Integer.parseInt(accountNo), accountName, minBalance, interest);
                System.out.println("Investment Account created successfully.");
                return;
            }
        }
        System.out.println("Investment account storage is full.");
    }

    private static void inquireBalance() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        for (BankAccounts account : bankAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                System.out.println("Balance: " + account.inquireBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void deposit() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Amount to Deposit: ");
        double amount = scanner.nextDouble();
        for (BankAccounts account : bankAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.deposit(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void withdraw() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();
        for (BankAccounts account : bankAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.withdraw(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void transferMoney() {
        System.out.print("Enter Your Account Number: ");
        int fromAccount = scanner.nextInt();
        System.out.print("Enter Recipient Account Number: ");
        int toAccount = scanner.nextInt();
        System.out.print("Enter Amount to Transfer: ");
        double amount = scanner.nextDouble();
        for (BankAccounts account : bankAccounts) {
            if (account != null && account.getAccountNo() == fromAccount) {
                account.transferMoney(bankAccounts, toAccount, amount);
                return;
            }
        }
        System.out.println("Account not found or transfer failed.");
    }

    private static void closeAccount() {
        System.out.print("Enter Account Number to Close: ");
        int accountNo = scanner.nextInt();
        for (BankAccounts account : bankAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.closeAccount();
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void encashCheck() {
        System.out.print("Enter Checking Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Check Amount: ");
        double amount = scanner.nextDouble();
        for (CheckingAccount account : checkingAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.encashCheck(amount);
                return;
            }
        }
        System.out.println("Checking Account not found.");
    }

    private static void chargeToCard() {
        System.out.print("Enter Credit Card Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Amount to Charge: ");
        double amount = scanner.nextDouble();
        for (CreditCardAccount account : creditCardAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.chargeToCard(amount);
                return;
            }
        }
        System.out.println("Credit Card Account not found.");
    }

    private static void payCard() {
        System.out.print("Enter Credit Card Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Payment Amount: ");
        double amount = scanner.nextDouble();
        for (CreditCardAccount account : creditCardAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.payCard(amount);
                return;
            }
        }
        System.out.println("Credit Card Account not found.");
    }

    private static void cashAdvance() {
        System.out.print("Enter Credit Card Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Cash Advance Amount: ");
        double amount = scanner.nextDouble();
        for (CreditCardAccount account : creditCardAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.cashAdvance(amount);
                return;
            }
        }
        System.out.println("Credit Card Account not found.");
    }

    private static void inquireAvailableCredit() {
        System.out.print("Enter Credit Card Account Number: ");
        int accountNo = scanner.nextInt();
        for (CreditCardAccount account : creditCardAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.inquireAvailableCredit();
                return;
            }
        }
        System.out.println("Credit Card Account not found.");
    }

    private static void addInvestment() {
        System.out.print("Enter Investment Account Number: ");
        int accountNo = scanner.nextInt();
        System.out.print("Enter Investment Amount: ");
        double amount = scanner.nextDouble();
        for (InvestmentAccount account : investmentAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.addInvestment(amount);
                return;
            }
        }
        System.out.println("Investment Account not found.");
    }

    private static void inquireInvestmentValue() {
        System.out.print("Enter Investment Account Number: ");
        int accountNo = scanner.nextInt();
        for (InvestmentAccount account : investmentAccounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                account.inquireInvestmentValue();
                return;
            }
        }
        System.out.println("Investment Account not found.");
    }
}
