package gui;

import javax.swing.*;
import java.awt.*;
import auth.UserManager;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;

    public LoginGUI() {
        setTitle("Login/Register");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // 🔹 Username Field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // 🔹 Password Field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // 🔹 Show Password Checkbox
        showPassword = new JCheckBox("Show Password");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(showPassword, gbc);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('•'); // Hide password
            }
        });

        // 🔹 Login Button
        JButton loginBtn = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginBtn, gbc);

        // 🔹 Register Button
        JButton registerBtn = new JButton("Register");
        gbc.gridx = 1;
        add(registerBtn, gbc);

        // ✅ Login Button Click
        loginBtn.addActionListener(e -> loginUser());

        // ✅ Register Button Click
        registerBtn.addActionListener(e -> registerUser());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loginUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Please enter both Username and Password!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String role = UserManager.authenticate(username, password);
        if (role != null) {
            JOptionPane.showMessageDialog(this, "✅ Welcome, " + username + "!");
            dispose(); // Close login window

            if (role.equals("admin")) {
                new AdminPanelGUI(username).setVisible(true); // Pass username
            } else {
                new StudentManagementGUI(username).setVisible(true); // Pass username
            }
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid Username or Password!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Please enter username and password!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = UserManager.registerUser(username, password, "user");
        if (success) {
            JOptionPane.showMessageDialog(this, "✅ Registration Successful! You can now log in.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Registration Failed!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}
