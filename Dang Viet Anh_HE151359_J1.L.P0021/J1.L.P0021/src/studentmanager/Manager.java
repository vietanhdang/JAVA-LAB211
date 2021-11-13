/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Benjamin
 */
public class Manager {

    public Input input = new Input();
    public Validation validation = new Validation();

    /**
     * This method requires the user to enter age from keyboard and return age
     *
     * @return choice
     */
    public int menu() {
        System.out.println("=====WELCOME TO STUDENT MANAGEMENT=====");
        System.out.println(" 1.	Create");
        System.out.println(" 2.	Find and Sort");
        System.out.println(" 3.	Update/Delete");
        System.out.println(" 4.	Report");
        System.out.println(" 5.	Exit");
        return input.getInputChoice("Enter your choice: ", 1, 5);
    }

    /**
     * This method requires the user to enter age from keyboard and return age
     *
     * @return positive integer number
     */
    public int menuUpdate() {
        System.out.println(" 1.	Student ID");
        System.out.println(" 2.	Student name");
        System.out.println(" 3.	Age");
        System.out.println(" 4.	Semester");
        System.out.println(" 5.	Course Name");
        System.out.println(" 0.	Exit");
        return input.getInputChoice("Enter your choice: ", 0, 5);
    }

    public void displayStudent(ArrayList<Student> listStudent) {
        System.out.printf("%-10s%-20s%-10s%-15s%-10s\n", "ID", "Student name", "Age", "Semester", "Course Name");
        //loop from first to last element of list student
        listStudent.forEach((student) -> {
            student.showInfo();
        });
    }

    public void createStudent(ArrayList<Student> listStudent) {
        //if number of students greater than 5 ask user continue or not
        if (listStudent.size() > 5) {
            if (!input.getInputYN("Number of students greater than 5, Do you want to continue (Y/N)?")) {
                return;
            }
        }
        //loop until user input not duplicate
        while (true) {
            String id = input.getInputID();
            String name = input.getInputString("Enter name student: ", 20);
            if (!validation.checkIdAndNameExist(listStudent, id, name)) {
                System.out.println("Student id already exists. Please re-enter.");
                continue;
            }
            int age = input.getInputAge();
            String semester = input.getInputString("Enter semester: ", 20);
            String course = input.getInputCourse();
            if (validation.checkStudentExist(listStudent, id, name, age, semester, course)) {
                System.out.println("Input student information already exists!");
            } else {
                listStudent.add(new Student(id, name, age, semester, course));
                System.out.println("ADD STUDENT SUCCESS.");
            }
            if (!input.getInputYN("Do you want to continue create (Y/N)?")) {
                return;
            }
        }
    }

    public void findAndSort(ArrayList<Student> listStudent) {
        System.out.println("============FIND STUDENT===============");
        //check list empty 
        if (validation.checkEmptyList(listStudent)) {
            return;
        }
        String name = input.getInputString("Enter name to search: ", 20);
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        for (Student student : listStudent) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                listStudentFindByName.add(student);
            }
        }
        if (listStudentFindByName.isEmpty()) {
            System.out.println(name + " : does not exist!");
        } else {
            System.out.println("Found " + listStudentFindByName.size() + " students named: " + name);
            Collections.sort(listStudentFindByName);
            displayStudent(listStudentFindByName);
        }
    }

    public Student getStudentByListFound(ArrayList<Student> listStudent) {
        int count = 1;
        System.out.printf("%-10s%-10s%-20s%-10s%-15s%-10s\n", "Number", "ID", "Student name", "Age", "Semester", "Course Name");
        //display list student found
        for (Student student : listStudent) {
            System.out.printf("%-10d%-10s%-20s%-10d%-15s%-10s\n", count, student.getId(),
                    student.getStudentName(), student.getAge(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        int choice = input.getInputChoice("Enter number you want to update/delete: ", 1, listStudent.size());
        return listStudent.get(choice - 1);
    }

    public void updateOrDelete(ArrayList<Student> listStudent) {
        System.out.println("============UPDATE / DELETE  STUDENT===============");
        if (validation.checkEmptyList(listStudent)) {
            return;
        }
        String id = input.getInputID();
        ArrayList<Student> listStudentFindById = new ArrayList<>();
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id)) {
                listStudentFindById.add(student);
            }
        }
        //check list empty
        if (listStudentFindById.isEmpty()) {
            System.out.println("STUDENT ID NOT FOUND.");
        } else {
            System.out.println("List student found: " + id);
            Student student = getStudentByListFound(listStudentFindById);
            //check user want to update or delete
            if (input.getInputUD("Do you want to update (U) or delete (D) student: ")) {
                while (true) {
                    System.out.println("=====UPDATE STUDENT INFORMATION=====");
                    System.out.printf("%-10s%-20s%-10s%-15s%-10s\n", "ID", "Student name", "Semester", "Age", "Course Name");
                    student.showInfo();
                    //Show menu option
                    int choice = menuUpdate();
                    switch (choice) {
                        case 1:
                            id = input.getInputID();
                            if (validation.checkIdExist(listStudent, id) != -1) {
                                System.out.println("STUDENT ID ALREADY EXISTS!");
                            } else {
                                student.setId(id);
                                System.out.println("ID UPDATE SUCCESSFUL");
                            }
                            break;
                        case 2:
                            student.setStudentName(input.getInputString("Enter name student: ", 20));
                            System.out.println("NAME UPDATE SUCCESSFUL");
                            break;
                        case 3:
                            student.setAge(input.getInputAge());
                            System.out.println("AGE UPDATE SUCCESSFUL");
                            break;
                        case 4:
                            student.setSemester(input.getInputString("Enter semester: ", 20));
                            System.out.println("SEMESTER UPDATE SUCCESSFUL");
                            break;
                        case 5:
                            student.setCourseName(input.getInputCourse());
                            System.out.println("COURSE UPDATE SUCCESSFUL");
                            break;
                        case 0:
                            return;
                    }
                }
            } else {
                listStudent.remove(student);
                System.out.println("DELETE SUCCESS.");
            }
        }
    }

    public void report(ArrayList<Student> listStudent) {
        if (validation.checkEmptyList(listStudent)) {
            return;
        }
        ArrayList<Report> listReport = new ArrayList<>();
        for (int i = 0; i < listStudent.size(); i++) {
            int total = 0;
            for (Student student : listStudent) {
                String id = student.getId();
                String courseName = student.getCourseName();
                String studentName = student.getStudentName();
                for (Student studentCountTotal : listStudent) {
                    if (id.equalsIgnoreCase(studentCountTotal.getId())
                            && courseName.equalsIgnoreCase(studentCountTotal.getCourseName())) {
                        total++;
                    }
                }
                if (checkReportExist(listReport, studentName,
                        courseName, total)) {
                    listReport.add(new Report(student.getStudentName(), courseName, total));
                }
                total = 0;
            }
        }
        //print report
        for (int i = 0; i < listReport.size(); i++) {
            System.out.printf("%-20s|%-10s|%-5d\n", listReport.get(i).getStudentName(),
                    listReport.get(i).getCourseName(), listReport.get(i).getTotalCourse());
        }
    }

    public static boolean checkReportExist(ArrayList<Report> listReport, String name,
            String course, int total) {
        for (Report report : listReport) {
            if (name.equalsIgnoreCase(report.getStudentName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

}