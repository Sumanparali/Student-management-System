package student;

import java.io.IOException;
import java.util.List;

public interface StudentOperations {
    void addStudent(Student student) throws IOException, ClassNotFoundException;

    void displayStudents() throws IOException, ClassNotFoundException;

    Student searchStudent(int rollNo) throws IOException, ClassNotFoundException;

    void deleteStudent(int rollNo) throws IOException, ClassNotFoundException;

    List<Student> readStudents() throws IOException, ClassNotFoundException;
}
