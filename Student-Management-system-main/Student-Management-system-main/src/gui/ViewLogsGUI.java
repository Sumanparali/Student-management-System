package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ViewLogsGUI extends JFrame {
    private JTextArea logArea;
    private static final String LOG_PATH = "data/logs.txt";

    public ViewLogsGUI() {
        setTitle("View Logs");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        loadLogs();

        add(new JScrollPane(logArea), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadLogs() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                logArea.append(line + "\n");
            }
        } catch (IOException e) {
            logArea.setText("No logs available.");
        }
    }
}
