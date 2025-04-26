package FinProjSerrano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankManagementGUI extends JFrame {

    private ArrayList<BankAccounts> bankAccounts = new ArrayList<>();
    private ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
    private ArrayList<CreditCardAccount> creditCardAccounts = new ArrayList<>();
    private ArrayList<InvestmentAccount> investmentAccounts = new ArrayList<>();

    public BankManagementGUI() {
        setTitle("Bank Account Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Create Account", createAccountPanel());
        tabbedPane.addTab("Deposit", depositPanel());
        tabbedPane.addTab("Withdraw", withdrawPanel());
        tabbedPane.addTab("Transfer", transferPanel());
        tabbedPane.addTab("Credit Card", creditCardPanel());
        tabbedPane.addTab("Investment", investmentPanel());
        tabbedPane.addTab("Close Account", closeAccountPanel());
        tabbedPane.addTab("View Balance", viewBalancePanel());

        add(tabbedPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JTextField accountNameField = new JTextField();
        String[] accountTypes = {"Bank", "Checking", "CreditCard", "Investment"};
        JComboBox<String> typeBox = new JComboBox<>(accountTypes);
        JTextField extraField = new JTextField();
        JLabel extraLabel = new JLabel("(Limit/Min Balance/Interest)");

        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            try {
                String accountNo = accountNoField.getText();
                String accountName = accountNameField.getText();
                double extra = Double.parseDouble(extraField.getText());

                switch (typeBox.getSelectedIndex()) {
                    case 0:
                        bankAccounts.add(new BankAccounts(accountNo, accountName));
                        break;
                    case 1:
                        CheckingAccount ca = new CheckingAccount(accountNo, accountName, extra);
                        checkingAccounts.add(ca);
                        bankAccounts.add(ca);
                        break;
                    case 2:
                        CreditCardAccount cca = new CreditCardAccount(accountNo, accountName, extra);
                        creditCardAccounts.add(cca);
                        bankAccounts.add(cca);
                        break;
                    case 3:
                        InvestmentAccount ia = new InvestmentAccount(accountNo, accountName, extra);
                        investmentAccounts.add(ia);
                        bankAccounts.add(ia);
                        break;
                }
                JOptionPane.showMessageDialog(this, "Account created successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNoField);
        panel.add(new JLabel("Account Name:"));
        panel.add(accountNameField);
        panel.add(new JLabel("Account Type:"));
        panel.add(typeBox);
        panel.add(extraLabel);
        panel.add(extraField);
        panel.add(new JLabel());
        panel.add(createButton);

        return panel;
    }

    private JPanel depositPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JTextField amountField = new JTextField();
        JButton depositButton = new JButton("Deposit");

        depositButton.addActionListener(e -> {
            try {
                BankAccounts account = findAccount(accountNoField.getText());
                account.deposit(Double.parseDouble(amountField.getText()));
                JOptionPane.showMessageDialog(this, "Deposit successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNoField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel());
        panel.add(depositButton);

        return panel;
    }

    private JPanel withdrawPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JTextField amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");

        withdrawButton.addActionListener(e -> {
            try {
                BankAccounts account = findAccount(accountNoField.getText());
                account.withdraw(Double.parseDouble(amountField.getText()));
                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNoField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel());
        panel.add(withdrawButton);

        return panel;
    }

    private JPanel transferPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JTextField amountField = new JTextField();
        JButton transferButton = new JButton("Transfer");

        transferButton.addActionListener(e -> {
            try {
                BankAccounts from = findAccount(fromField.getText());
                BankAccounts to = findAccount(toField.getText());
                double amount = Double.parseDouble(amountField.getText());

                from.withdraw(amount);
                to.deposit(amount);
                JOptionPane.showMessageDialog(this, "Transfer successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("From Account:"));
        panel.add(fromField);
        panel.add(new JLabel("To Account:"));
        panel.add(toField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel());
        panel.add(transferButton);

        return panel;
    }

    private JPanel creditCardPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JTextField amountField = new JTextField();
        JButton chargeButton = new JButton("Charge to Card");
        JButton payButton = new JButton("Pay Card");

        chargeButton.addActionListener(e -> {
            try {
                CreditCardAccount account = findCreditCard(accountNoField.getText());
                account.chargeToCard(Double.parseDouble(amountField.getText()));
                JOptionPane.showMessageDialog(this, "Charge successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        payButton.addActionListener(e -> {
            try {
                CreditCardAccount account = findCreditCard(accountNoField.getText());
                account.payCard(Double.parseDouble(amountField.getText()));
                JOptionPane.showMessageDialog(this, "Payment successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Credit Card Account:"));
        panel.add(accountNoField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(chargeButton);
        panel.add(payButton);

        return panel;
    }

    private JPanel investmentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JButton computeButton = new JButton("Compute Interest");

        computeButton.addActionListener(e -> {
            try {
                InvestmentAccount account = findInvestment(accountNoField.getText());
                account.computeMonthlyInterest();
                JOptionPane.showMessageDialog(this, "Interest computed and added.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Investment Account:"));
        panel.add(accountNoField);
        panel.add(new JLabel());
        panel.add(computeButton);

        return panel;
    }

    private JPanel closeAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JButton closeButton = new JButton("Close Account");

        closeButton.addActionListener(e -> {
            try {
                BankAccounts account = findAccount(accountNoField.getText());
                account.closeAccount();
                JOptionPane.showMessageDialog(this, "Account closed successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNoField);
        panel.add(new JLabel());
        panel.add(closeButton);

        return panel;
    }

    private JPanel viewBalancePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField accountNoField = new JTextField();
        JButton viewButton = new JButton("View Balance");

        viewButton.addActionListener(e -> {
            try {
                BankAccounts account = findAccount(accountNoField.getText());
                JOptionPane.showMessageDialog(this, "Balance: " + account.inquireBalance());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNoField);
        panel.add(new JLabel());
        panel.add(viewButton);

        return panel;
    }

    private BankAccounts findAccount(String accNo) throws Exception {
        for (BankAccounts acc : bankAccounts) {
            if (String.valueOf(acc.getAccountNo()).equals(accNo)) {
                return acc;
            }
        }
        throw new Exception("Account not found.");
    }

    private CreditCardAccount findCreditCard(String accNo) throws Exception {
        for (CreditCardAccount acc : creditCardAccounts) {
            if (String.valueOf(acc.getAccountNo()).equals(accNo)) {
                return acc;
            }
        }
        throw new Exception("Credit Card Account not found.");
    }

    private InvestmentAccount findInvestment(String accNo) throws Exception {
        for (InvestmentAccount acc : investmentAccounts) {
            if (String.valueOf(acc.getAccountNo()).equals(accNo)) {
                return acc;
            }
        }
        throw new Exception("Investment Account not found.");
    }

    public static void main(String[] args) {
        new BankManagementGUI();
    }
}
