package gui;

import student.Student;
import student.StudentManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class StudentManagementGUI extends JFrame {
    private StudentManager manager = new StudentManager();
    private JTable table;
    private DefaultTableModel tableModel;
    private String loggedInUser;
    private JTextField searchField; // 🔹 Search Field

    public StudentManagementGUI(String username) {
        this.loggedInUser = username;
        setTitle("Student Management - " + username);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Menu Bar (Displays Logged-in User)
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("User: " + username);
        JMenuItem logoutItem = new JMenuItem("Logout");

        logoutItem.addActionListener(e -> logout());
        userMenu.add(logoutItem);
        menuBar.add(userMenu);
        setJMenuBar(menuBar);

        // 🔹 Student Table
        String[] columns = { "Roll No", "Name", "Course", "Age" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        loadTableData();

        // 🔹 Buttons Panel
        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Add Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton searchBtn = new JButton("Search"); // 🔹 Search Button

        searchField = new JTextField(15); // 🔹 Search Input

        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchBtn);
        panel.add(addBtn);
        panel.add(deleteBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // 🔹 Button Actions
        addBtn.addActionListener(this::addStudent);
        deleteBtn.addActionListener(this::deleteStudent);
        searchBtn.addActionListener(this::searchStudent); // 🔹 Search Button Click

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Name:");
        int rollNo = Integer.parseInt(JOptionPane.showInputDialog("Roll No:"));
        String course = JOptionPane.showInputDialog("Course:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Age:"));

        manager.addStudent(new Student(name, rollNo, course, age));
        loadTableData();
    }

    private void deleteStudent(ActionEvent e) {
        int rollNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll No to delete:"));
        manager.deleteStudent(rollNo);
        loadTableData();
    }

    private void searchStudent(ActionEvent e) {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            loadTableData();
            return;
        }

        tableModel.setRowCount(0);
        for (Student s : manager.getStudents()) {
            if (s.getName().toLowerCase().contains(query) || String.valueOf(s.getRollNo()).equals(query)) {
                tableModel.addRow(new Object[] { s.getRollNo(), s.getName(), s.getCourse(), s.getAge() });
            }
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        for (Student s : manager.getStudents()) {
            tableModel.addRow(new Object[] { s.getRollNo(), s.getName(), s.getCourse(), s.getAge() });
        }
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "✅ Logged out successfully!");
        dispose(); // Close current window
        new LoginGUI().setVisible(true); // Return to login screen
    }
}
