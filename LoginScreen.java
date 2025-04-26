package FinProjSerrano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel createAccountLabel;
    private JLabel forgotPasswordLabel;
    private static HashMap<String, String> users = new HashMap<>();

    public LoginScreen() {
        setTitle("Bank Management System - Login");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 230, 230));

        JLabel titleLabel = new JLabel("\uD83D\uDD10 Bank Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(90, 20, 250, 30);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Enter username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameLabel.setBounds(50, 70, 120, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 90, 300, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordLabel.setBounds(50, 130, 120, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 150, 300, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        add(passwordField);

        createAccountLabel = new JLabel("Create Account?");
        createAccountLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        createAccountLabel.setForeground(Color.BLUE);
        createAccountLabel.setBounds(50, 190, 100, 20);
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(createAccountLabel);

        forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setBounds(250, 190, 120, 20);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(forgotPasswordLabel);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(140, 220, 120, 30);
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if ((username.equals("admin") && password.equals("admin")) ||
                        (users.containsKey(username) && users.get(username).equals(password))) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new BankManagementGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createAccountLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTextField newUser = new JTextField();
                JPasswordField newPass = new JPasswordField();
                Object[] message = {
                        "New Username:", newUser,
                        "New Password:", newPass
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Create New Account", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String username = newUser.getText();
                    String password = String.valueOf(newPass.getPassword());
                    if (!username.isEmpty() && !password.isEmpty()) {
                        users.put(username, password);
                        JOptionPane.showMessageDialog(null, "Account created successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String username = JOptionPane.showInputDialog("Enter your username:");
                if (users.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Your password is: " + users.get(username));
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
