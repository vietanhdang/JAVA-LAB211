/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

/**
 *
 * @author Benjamin
 */
public class Student implements Comparable<Student> {

    private String id;
    private String studentName;
    private int age;
    private String semester;
    private String courseName;

    public Student() {
    }

    public Student(String id, String studentName, int age, String semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void showInfo() {
        System.out.printf("%-10s%-20s%-10d%-15s%-10s\n", id, studentName, age, semester, courseName);
    }

    @Override
    public int compareTo(Student o) {
        return o.age - this.age;
    }
}
