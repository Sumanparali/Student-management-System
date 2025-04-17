package auth;

import java.io.*;

public class UserManager {
    private static final String FILE_PATH = "data/users.txt";
    private static final String LOG_FILE = "data/logs.txt";

    // 🔹 Method to Register a New User
    public static boolean registerUser(String username, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + "," + password + "," + role);
            writer.newLine();
            logAction("New user registered: " + username + " (Role: " + role + ")");
            return true; // Registration successful
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Registration failed
    }

    // 🔹 Method to Authenticate User
    public static String authenticate(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    String storedUsername = details[0].trim();
                    String storedPassword = details[1].trim();
                    String role = details[2].trim();

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        logAction(username + " logged in at " + java.time.LocalDateTime.now());
                        return role; // Return user role (admin/user)
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Login failed
    }

    // 🔹 Method to Log User Logout
    public static void logout(String username) {
        logAction(username + " logged out at " + java.time.LocalDateTime.now());
    }

    // 🔹 Method to Log Actions (User Activities)
    private static void logAction(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Debugging / Testing
    public static void main(String[] args) {
        System.out.println("Absolute path: " + new File(FILE_PATH).getAbsolutePath());

        // Manually register users for testing
        registerUser("Hariom", "admin@qwerty", "admin");
        registerUser("user1", "userpassword", "user");

        // Test authentication
        String role = authenticate("Hariom", "admin@qwerty");
        System.out.println("Hariom Role: " + (role != null ? role : "Invalid Credentials"));

        // Test logout
        logout("Hariom");
    }
}
