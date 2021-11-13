/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benjamin
 */
public class Input {

    /**
     * declare a variable scanner to use Input from keyboard
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * declare a final variable logger to use logging for {@code Input} class
     */
    private static final Logger logger = Logger.getLogger(Input.class.getName());

    /**
     * This method requires the user to enter a value from min to max from the
     * keyboard and returns an integer
     *
     * @param message notice to enter
     * @param min (min value)
     * @param max (max value)
     * @return integer in the range min to max
     */
    public int getInputChoice(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    // if the value is between min and max then return
                    return choice;
                } else {
                    logger.log(Level.WARNING, "Choice must in [{0}-{1}], enter again !", new Object[]{min, max});
                }
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Choice must in [{0}-{1}], enter again !", new Object[]{min, max});
            }
        }
    }

    /**
     * This method requires the user to enter a string from keyboard (with a
     * specified length) and returns a string
     *
     * @param message notice to enter
     * @param length fixed length
     * @return string
     */
    public String getInputString(String message, int length) {
        while (true) {
            System.out.print(message);
            String result = scanner.nextLine().trim();
            if (result.isEmpty() || result.length() > length) {
                // if the string is empty or the string length is greater than the fixed length, re-enter it
                logger.log(Level.WARNING, "Input length cannot be empty or must be less than {0} enter again!", length);
            } else {
                return result;
            }
        }
    }

    /**
     * This method requires the user to enter an id from keyboard and return id
     *
     * @return string
     */
    public String getInputID() {
        while (true) {
            try {
                System.out.print("Enter ID (ex: HE151359):  ");
                String result = scanner.nextLine().trim();
                if (result.matches("[a-zA-Z]{2}[1-9]{6}")) {
                    // check if string matches FPT student code
                    return result.toUpperCase();
                } else {
                    logger.warning("Invalid ID, enter again!");
                }
            } catch (Exception e) {
                logger.warning("Invalid ID, enter again!");
            }
        }
    }

    /**
     * This method requires the user to enter age from keyboard and return age
     *
     * @return positive integer number
     */
    public int getInputAge() {
        while (true) {
            try {
                System.out.print("Enter age (ex: 21):  ");
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (number > 0) {
                    return number;
                }
                System.out.println("Invalid age, enter again!");
            } catch (NumberFormatException e) {
                logger.warning("Invalid age, enter again!");
            }
        }
    }

    /**
     * This method requires the user to enter course from keyboard and return
     * course
     *
     * @return string
     */
    public String getInputCourse() {
        //loop until user input correct
        while (true) {
            String result = getInputString("Enter name course (ex: java - .net - c/c++): ", 10);
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            logger.warning("There are only three courses: java, .net, c/c++");
        }
    }

    /**
     * This method requires the user to enter U or D from the keyboard and
     * returns true or false
     *
     * @param message input message
     * @return true if it is U and not otherwise
     */
    public boolean getInputUD(String message) {
        return getInputString(message, 1).equalsIgnoreCase("u");
    }

    /**
     * This method requires the user to enter Y or N from the keyboard and
     * returns true false
     *
     * @param message input message
     * @return true if it is Y and not otherwise
     */
    public boolean getInputYN(String message) {
        return getInputString(message, 1).equalsIgnoreCase("y");
    }
}
