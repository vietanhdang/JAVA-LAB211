/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0071
 * Title: Task Manager
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-10-9         1.0               AnhDV          First Implement
 */
package manager;

import entity.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code InputReader} class contains methods getInputOption,
 * getInputString, getTaskIndexId, getInputTaskTypeId, getInputDate,
 * getInputPlan, getInputYN that can be used to ask the user for input from the
 * keyboard and return the entered data.
 *
 * @author Dang Viet Anh
 */
public class InputReader {

    /**
     * declare a variable SCANNER to use InputReader from keyboard
     */
    private final static Scanner SCANNER = new Scanner(System.in);

    /**
     * declare a final variable LOGGER to use logging for {@code InputReader}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(InputReader.class.getName());

    private Validator validator = new Validator(); // Call Validator class to checkValidId valid of input

    /**
     * This method requires the user to enter from keyboard and returns an
     * integer value from minimum input number to maximum input number from the
     * key
     *
     * @param message notify user input
     * @param min minimum input number
     * @param max maximum input number
     * @return integer value
     */
    int getInputNumberInRange(String message, int min, int max) {
        while (true) {
            System.out.print(message); // notify user input
            String inputNumber = SCANNER.nextLine().trim();
            if (validator.checkValidNumberInRange(inputNumber, min, max)) {
                return Integer.parseInt(inputNumber);
            }
        }
    }

    /**
     * This method requires the user to enter a string into the keyboard (with a
     * specified length) and returns a string
     *
     * @param message notify user input
     * @param length fixed length
     * @return string
     */
    String getInputString(String message, int length) {
        while (true) {
            System.out.print(message); //notify user input 
            String inputString = SCANNER.nextLine().trim();
            if (!inputString.isEmpty() && inputString.length() <= length) {
                return inputString;
            }
            // if the string is empty or the string length is greater than the fixed length, re-enter it
            LOGGER.log(Level.WARNING, "Input length cannot be empty or must be less than {0} enter again!", length);
        }
    }

    /**
     * This method ask user for input a integer until it is a positive integer.
     *
     * @param message notify user input
     * @return a positive integer.
     */
    int getInputId(String message, ArrayList<Task> taskList) {
        while (true) {
            try {
                System.out.print(message);
                int taskId = Integer.parseInt(SCANNER.nextLine().trim());
                if (validator.checkValidId(taskId, taskList)) {
                    return taskId;
                }
                System.err.println("ID not exist");
            } catch (NumberFormatException ex) {
                System.err.println("Your input is invalid (It's must be a positive integer). Please try again!");
            }
        }
    }

    /**
     * This method get user input a Date until it is a valid date in format
     * "month-date-year".
     *
     * @param message out to console.
     * @param update take to check option is update or not. If it's update and
     * user input nope return nope.
     * @return a positive integer.
     */
    LocalDate getInputDate(String message, int length) {
        while (true) {
            LocalDate inputDate = validator.checkValidDate(getInputString(message, length));
            if (inputDate != null) {
                return inputDate;
            }
        }
    }

    /**
     * This method requires the user to enter a time plan from between 8.0 -
     * 17.5 (8h -> 17h30) and returns a string
     *
     * @param message notice to enter
     * @param update if true return string null
     * @return plan number in range 8.0 -> 17.5
     */
    String getInputPlanFrom(String message, boolean update) {
        while (true) {
            System.out.print(message); // notify user input
            String inputPlan = SCANNER.nextLine().trim();
            if (update && inputPlan.equalsIgnoreCase("nope")) {
                return "-1";  // it's update option and user input nope
            }
            if (validator.checkValidTime(inputPlan) && (Float.parseFloat(inputPlan) != 17.5f)) {
                return inputPlan;
            }
        }
    }

    /**
     * This method requires the user to enter a time plan to from between 8.0 -
     * 17.5 (8h -> 17h30) and returns a string
     *
     * @param message notice to enter
     * @param update if true return string null
     * @return plan number in range 8.0 -> 17.5
     */
    String getInputPlanTo(String message, boolean update) {
        while (true) {
            System.out.print(message); // notify user input
            String inputPlan = SCANNER.nextLine().trim();
            if (update && inputPlan.equalsIgnoreCase("nope")) {
                return "-1";  // it's update option and user input nope
            }
            if (validator.checkValidTime(inputPlan) && (Float.parseFloat(inputPlan) != 8.0f)) {
                return inputPlan;
            }
        }
    }

    /**
     * This method requires the user to enter Y or N from the keyboard and
     * returns true false
     *
     * @param message notice to enter
     * @return true if it is Y and not otherwise
     */
    public boolean getInputYN(String message) {
        while (true) {
            System.out.print(message); // notify user input
            String inputString = SCANNER.nextLine().trim();
            if (inputString.equalsIgnoreCase("Y")) {
                return true;
            }
            if (inputString.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Must be Y or N. Enter again!");
        }
    }
}
