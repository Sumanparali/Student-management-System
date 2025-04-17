package student;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int rollNo;
    private String course;
    private int age;

    public Student(String name, int rollNo, String course, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.course = course;
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return rollNo + " - " + name + " (" + course + "), Age: " + age;
    }
}
