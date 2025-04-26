package FinProjSerrano;

import java.util.Scanner;
import java.util.ArrayList;

public class MainAccounts {
    private static ArrayList<BankAccounts> bankAccounts = new ArrayList<>();
    private static ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
    private static ArrayList<CreditCardAccount> creditCardAccounts = new ArrayList<>();
    private static ArrayList<InvestmentAccount> investmentAccounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
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
                scanner.nextLine(); // clear the newline

                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: inquireBalance(); break;
                    case 3: deposit(); break;
                    case 4: withdraw(); break;
                    case 5: transferMoney(); break;
                    case 6: closeAccount(); break;
                    case 7: encashCheck(); break;
                    case 8: chargeToCard(); break;
                    case 9: payCard(); break;
                    case 10: cashAdvance(); break;
                    case 11: inquireAvailableCredit(); break;
                    case 12: addInvestment(); break;
                    case 13: inquireInvestmentValue(); break;
                    case 14:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static boolean isValidAccountNumber(String accountNo) {
        return accountNo.matches("\\d{9}");
    }

    private static void createAccount() {
        System.out.println("Choose account type: 1. BankAccount 2. Checking 3. CreditCard 4. Investment");
        int type;
        do {
            type = scanner.nextInt();
            if (type < 1 || type > 4) {
                System.out.println("Invalid choice. Please select a number between 1 and 4.");
            }
        } while (type < 1 || type > 4);
        scanner.nextLine();

        String accountNo;
        do {
            System.out.print("Enter Account Number (9 digits): ");
            accountNo = scanner.nextLine();
            if (!isValidAccountNumber(accountNo)) {
                System.out.println("Invalid account number. Please enter exactly 9 digits.");
            }
        } while (!isValidAccountNumber(accountNo));

        System.out.print("Enter Account Name: ");
        String accountName = scanner.nextLine();

        switch (type) {
            case 1: addBankAccount(accountNo, accountName); break;
            case 2: addCheckingAccount(accountNo, accountName); break;
            case 3: addCreditCardAccount(accountNo, accountName); break;
            case 4: addInvestmentAccount(accountNo, accountName); break;
        }
    }

    private static void addBankAccount(String accountNo, String accountName) {
        try {
            bankAccounts.add(new BankAccounts(accountNo, accountName));
            System.out.println("Bank Account created successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addCheckingAccount(String accountNo, String accountName) {
        System.out.print("Enter Minimum Balance: ");
        double minBalance = scanner.nextDouble();
        checkingAccounts.add(new CheckingAccount(accountNo, accountName, minBalance));
        bankAccounts.add(checkingAccounts.get(checkingAccounts.size() - 1));
        System.out.println("Checking Account created successfully.");
    }

    private static void addCreditCardAccount(String accountNo, String accountName) {
        System.out.print("Enter Credit Limit: ");
        double creditLimit = scanner.nextDouble();
        creditCardAccounts.add(new CreditCardAccount(accountNo, accountName, creditLimit));
        bankAccounts.add(creditCardAccounts.get(creditCardAccounts.size() - 1));
        System.out.println("Credit Card Account created successfully.");
    }

    private static void addInvestmentAccount(String accountNo, String accountName) {
        System.out.print("Enter Interest Rate: ");
        double interest = scanner.nextDouble();
        investmentAccounts.add(new InvestmentAccount(accountNo, accountName, interest));
        bankAccounts.add(investmentAccounts.get(investmentAccounts.size() - 1));
        System.out.println("Investment Account created successfully.");
    }

    private static void inquireBalance() {
        System.out.print("Enter Account Number: ");
        String accountNo = scanner.nextLine();
        for (BankAccounts account : bankAccounts) {
            if (String.valueOf(account.getAccountNo()).equals(accountNo)) {
                System.out.println("Balance: " + account.inquireBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void deposit() {
        try {
            System.out.print("Enter Account Number: ");
            String accountNo = scanner.nextLine();
            System.out.print("Enter Amount to Deposit: ");
            double amount = scanner.nextDouble();

            for (BankAccounts account : bankAccounts) {
                if (String.valueOf(account.getAccountNo()).equals(accountNo)) {
                    account.deposit(amount);
                    return;
                }
            }
            System.out.println("Account not found.");
        } catch (Exception e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    private static void withdraw() {
        try {
            System.out.print("Enter Account Number: ");
            String accountNo = scanner.nextLine();
            System.out.print("Enter Amount to Withdraw: ");
            double amount = scanner.nextDouble();

            for (BankAccounts account : bankAccounts) {
                if (String.valueOf(account.getAccountNo()).equals(accountNo)) {
                    account.withdraw(amount);
                    return;
                }
            }
            System.out.println("Account not found.");
        } catch (Exception e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private static void transferMoney() {
        try {
            System.out.print("Enter Your Account Number: ");
            String fromAccount = scanner.nextLine();
            System.out.print("Enter Recipient Account Number: ");
            String toAccount = scanner.nextLine();
            System.out.print("Enter Amount to Transfer: ");
            double amount = scanner.nextDouble();

            BankAccounts sender = null;
            BankAccounts receiver = null;

            for (BankAccounts account : bankAccounts) {
                if (String.valueOf(account.getAccountNo()).equals(fromAccount)) {
                    sender = account;
                } else if (String.valueOf(account.getAccountNo()).equals(toAccount)) {
                    receiver = account;
                }
            }

            if (sender != null && receiver != null) {
                sender.withdraw(amount);
                receiver.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Sender or recipient account not found.");
            }
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private static void closeAccount() {
        System.out.print("Enter Account Number to Close: ");
        String accountNo = scanner.nextLine();
        for (BankAccounts account : bankAccounts) {
            if (String.valueOf(account.getAccountNo()).equals(accountNo)) {
                account.closeAccount();
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void encashCheck() {
        try {
            System.out.print("Enter Checking Account Number: ");
            int accountNo = scanner.nextInt();
            System.out.print("Enter Check Amount: ");
            double amount = scanner.nextDouble();

            for (CheckingAccount account : checkingAccounts) {
                if (account.getAccountNo() == accountNo) {
                    account.withdraw(amount);
                    System.out.println("Check encashed successfully.");
                    return;
                }
            }
            System.out.println("Checking Account not found.");
        } catch (Exception e) {
            System.out.println("Check encash failed: " + e.getMessage());
        }
    }

    private static void chargeToCard() {
        try {
            System.out.print("Enter Credit Card Account Number: ");
            int accountNo = scanner.nextInt();
            System.out.print("Enter Amount to Charge: ");
            double amount = scanner.nextDouble();

            for (CreditCardAccount account : creditCardAccounts) {
                if (account.getAccountNo() == accountNo) {
                    account.chargeToCard(amount);
                    return;
                }
            }
            System.out.println("Credit Card Account not found.");
        } catch (Exception e) {
            System.out.println("Charge failed: " + e.getMessage());
        }
    }

    private static void payCard() {
        try {
            System.out.print("Enter Credit Card Account Number: ");
            int accountNo = scanner.nextInt();
            System.out.print("Enter Payment Amount: ");
            double amount = scanner.nextDouble();

            for (CreditCardAccount account : creditCardAccounts) {
                if (account.getAccountNo() == accountNo) {
                    account.payCard(amount);
                    return;
                }
            }
            System.out.println("Credit Card Account not found.");
        } catch (Exception e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }

    private static void cashAdvance() {
        try {
            System.out.print("Enter Credit Card Account Number: ");
            int accountNo = scanner.nextInt();
            System.out.print("Enter Cash Advance Amount: ");
            double amount = scanner.nextDouble();

            for (CreditCardAccount account : creditCardAccounts) {
                if (account.getAccountNo() == accountNo) {
                    account.chargeToCard(amount);
                    return;
                }
            }
            System.out.println("Credit Card Account not found.");
        } catch (Exception e) {
            System.out.println("Cash Advance failed: " + e.getMessage());
        }
    }

    private static void inquireAvailableCredit() {
        try {
            System.out.print("Enter Credit Card Account Number: ");
            int accountNo = scanner.nextInt();

            for (CreditCardAccount account : creditCardAccounts) {
                if (account.getAccountNo() == accountNo) {
                    System.out.println("Available Credit: " + (account.getCreditLimit() - account.getCharges()));
                    return;
                }
            }
            System.out.println("Credit Card Account not found.");
        } catch (Exception e) {
            System.out.println("Inquiry failed: " + e.getMessage());
        }
    }

    private static void addInvestment() {
        try {
            System.out.print("Enter Investment Account Number: ");
            int accountNo = scanner.nextInt();
            System.out.print("Enter Investment Amount: ");
            double amount = scanner.nextDouble();

            for (InvestmentAccount account : investmentAccounts) {
                if (account.getAccountNo() == accountNo) {
                    account.deposit(amount);
                    return;
                }
            }
            System.out.println("Investment Account not found.");
        } catch (Exception e) {
            System.out.println("Investment failed: " + e.getMessage());
        }
    }

    private static void inquireInvestmentValue() {
        System.out.print("Enter Investment Account Number: ");
        int accountNo = scanner.nextInt();

        for (InvestmentAccount account : investmentAccounts) {
            if (account.getAccountNo() == accountNo) {
                account.computeMonthlyInterest();
                return;
            }
        }
        System.out.println("Investment Account not found.");
    }
}
