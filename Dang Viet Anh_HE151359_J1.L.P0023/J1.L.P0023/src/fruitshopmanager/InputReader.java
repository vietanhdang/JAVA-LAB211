/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0023
 * Title: Fruit Shop Manager
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-10-10         1.0               AnhDV          First Implement
 */
package fruitshopmanager;

import entity.Fruit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code InputReader} class contains methods getInputString,
 * getInputNumberInRange, getInputInteger, getInputFloat, getInputId,
 * getInputYesNo that can be used to ask the user for input from the keyboard
 * and return the entered data.
 *
 * @author Dang Viet Anh
 */
public class InputReader extends Validator {

    /**
     * declare a variable SCANNER to use InputReader from keyboard
     */
    private final static Scanner SCANNER = new Scanner(System.in);

    /**
     * declare a final variable LOGGER to use logging for {@code InputReader}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(InputReader.class.getName());

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
            System.out.print(message);
            String inputString = SCANNER.nextLine().trim(); // take user input
            if (checkValidString(inputString, length)) {
                return inputString;     // Valid String
            }
            LOGGER.warning("Your input is invalid. Please try again!"); // if empty string, enter again
        }
    }

    /**
     * This method requires the user to enter from keyboard and returns an
     * integer value from minimum input number to maximum input number from the
     * key
     *
     * @param message notify user input
     * @param min minimum input number
     * @param max maximum input number
     * @param error message error for input
     * @return integer in range [min, max]
     */
    int getInputNumberInRange(String message, int min, int max, String error) {
        while (true) {
            System.out.print(message); // notify user input
            String inputNumber = SCANNER.nextLine().trim();
            if (checkValidNumberInRange(inputNumber, min, max, error)) {
                return Integer.parseInt(inputNumber);
            }
        }
    }

    /**
     * This method ask user for input an integer until it is a positive integer
     * and greater than minValue
     *
     * @param message out to console.
     * @param minValue - min value method can return.
     * @return a positive integer
     */
    int getInputInteger(String message, int minValue) {
        while (true) {
            System.out.print(message);
            String inputNumber = SCANNER.nextLine().trim();
            if (checkValidIntegerNumber(inputNumber, minValue)) {
                return Integer.parseInt(inputNumber);
            }
        }
    }

    /**
     * This method ask user for input a double until it is a real number and
     * greater than minValue.
     *
     * @param message out to console.
     * @param minValue - min value method can return.
     * @return a positive real number.
     */
    float getInputFloat(String message, int minValue) {
        while (true) {
            System.out.print(message);
            String inputNumber = SCANNER.nextLine().trim();
            if (checkValidFloatNumber(inputNumber, minValue)) {
                return Float.parseFloat(inputNumber);
            }
        }
    }

    /**
     * This method get user input from keyboard until it's an id of fruit that
     * exist in list.
     *
     * @param fruitsList - array list contain all fruits
     * @return an integer represent for id of fruit that exist in list.
     */
    int getInputId(ArrayList<Fruit> fruitsList) {
        while (true) {
            int inputId = getInputInteger("Enter fruit ID: ", 0);
            if (checkFruitIdExists(fruitsList, inputId)) {
                return inputId;
            }
            LOGGER.log(Level.WARNING, "ID {0} does not exist", inputId);
        }
    }

    /**
     * This method requires the user to enter Y or N from the keyboard and
     * returns true false
     *
     * @param message notice to enter
     * @return true if it is Y and not otherwise
     */
    boolean getInputYesNo(String message) {
        while (true) {
            System.out.print(message); // notify user input
            String inputString = SCANNER.nextLine().trim();
            if (inputString.equalsIgnoreCase("Y")) {
                return true;
            }
            if (inputString.equalsIgnoreCase("N")) {
                return false;
            }
            LOGGER.warning("Must be Y or N. Enter again!");
        }
    }
}
