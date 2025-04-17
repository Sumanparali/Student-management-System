package student;

import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "data/students.txt";
    private List<Student> students = new ArrayList<>();

    public StudentManager() {
        loadStudents();
    }

    private void loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public void deleteStudent(int rollNo) {
        students.removeIf(s -> s.getRollNo() == rollNo);
        saveStudents();
    }

    public List<Student> getStudents() {
        return students;
    }
}
