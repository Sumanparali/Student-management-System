package gui;

import javax.swing.*;
import java.awt.*;

public class AdminPanelGUI extends JFrame {
    public AdminPanelGUI(String adminUsername) {
        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Welcome, " + adminUsername + "!");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        JButton manageUsersButton = new JButton("Manage Users");
        JButton viewLogsButton = new JButton("View Logs");
        JButton logoutButton = new JButton("Logout");

        // Open Manage Users GUI
        manageUsersButton.addActionListener(e -> new ManageUsersGUI());

        // Open View Logs GUI
        viewLogsButton.addActionListener(e -> new ViewLogsGUI());

        // Logout and return to login screen
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logging out...");
            new LoginGUI();
            dispose();
        });

        manageUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewLogsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(manageUsersButton);
        add(viewLogsButton);
        add(logoutButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
