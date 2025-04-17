package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManageUsersGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private static final String FILE_PATH = "data/users.txt";

    public ManageUsersGUI() {
        setTitle("Manage Users");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"Username", "Role"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        loadUsers();

        JPanel panel = new JPanel();
        JButton deleteBtn = new JButton("Delete User");
        JButton updateRoleBtn = new JButton("Update Role");

        panel.add(deleteBtn);
        panel.add(updateRoleBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        deleteBtn.addActionListener(this::deleteUser);
        updateRoleBtn.addActionListener(this::updateUserRole);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    tableModel.addRow(new Object[]{details[0], details[2]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        String username = (String) tableModel.getValueAt(row, 0);
        List<String> updatedUsers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(username + ",")) {
                    updatedUsers.add(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String user : updatedUsers) {
                bw.write(user);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        loadUsers();
    }

    private void updateUserRole(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update.");
            return;
        }

        String username = (String) tableModel.getValueAt(row, 0);
        String newRole = JOptionPane.showInputDialog(this, "Enter new role (admin/user):");

        List<String> updatedUsers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username)) {
                    updatedUsers.add(username + "," + details[1] + "," + newRole);
                } else {
                    updatedUsers.add(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String user : updatedUsers) {
                bw.write(user);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        loadUsers();
    }
}
