/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class Validation {

    public int checkIdExist(ArrayList<Student> listStudent, String id) {
        for (int i = 0; i < listStudent.size(); i++) {
            if (id.equalsIgnoreCase(listStudent.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkIdAndNameExist(ArrayList<Student> listStudent, String id, String name) {
        for (Student student : listStudent) {
            if (id.equalsIgnoreCase(student.getId())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkStudentExist(ArrayList<Student> listStudent, String id,
            String studentName, int age, String semester, String courseName) {
        for (Student student : listStudent) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && (student.getAge() == age)
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmptyList(ArrayList<Student> listStudent) {
        if (listStudent.isEmpty()) {
            System.out.println("List empty.");
            return true;
        }
        return false;
    }
}