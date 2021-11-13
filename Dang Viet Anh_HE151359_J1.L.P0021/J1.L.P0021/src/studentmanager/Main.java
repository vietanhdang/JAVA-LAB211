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
public class Main {

    public static void main(String[] args) {
        ArrayList<Student> ls = new ArrayList<>();
        Manager manager = new Manager();
        ls.add(new Student("HE151359", "Pham Ngoc Hoa", 20, "Spring", "java"));
        ls.add(new Student("HE151359", "Pham Ngoc Hoa", 20, "Fall", "java"));
        ls.add(new Student("HE151359", "Pham Ngoc Hoa", 20, "Fall", ".net"));
        ls.add(new Student("HE151358", "Do Quang Hiep", 21, "Summer", ".net"));
        ls.add(new Student("HE151357", "Nguyen Xuan Cuong", 22, "Spring", "c/c++"));
        //loop until user want to exit program
        while (true) {
            //Show menu option
            int choice = manager.menu();
            switch (choice) {
                case 1:
                    manager.createStudent(ls);
                    break;
                case 2:
                    manager.findAndSort(ls);
                    break;
                case 3:
                    manager.updateOrDelete(ls);
                    break;
                case 4:
                    manager.report(ls);
                    break;
                case 5:
                    return;
            }

        }
    }
}
