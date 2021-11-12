/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0011
 * Title: Base System Conversion. 
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-11-1        1.0               AnhDV           First Implement
 */
package baseconversion;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code InputReader} class contains methods getInputOption,
 * getInputBinaryNumber, getInputOctalNumber, getInputDecimalNumber,
 * getInputHexadecimalNumber that can be used to ask the user to enter number
 * from the keyboard.
 *
 * @author Dang Viet Anh
 */
public class InputReader {

    /**
     * declare a variable SCANNER to use Input from keyboard
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * declare a final variable LOGGER to use logging for {@code InputReader}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(InputReader.class.getName());

    /**
     * This method requires the user to enter a value from minimum input number
     * to maximum input number from the keyboard and returns an integer
     *
     * @param message notify user input
     * @param min minimum number
     * @param max maximum number
     * @return value is in the range min to max
     */
    int getInputOption(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message); // notify user input
                int choice = Integer.parseInt(SCANNER.nextLine());
                if (choice >= min && choice <= max) {
                    // if the value is between min and max then return choice
                    return choice;
                }
                // if the value is not between min and max then ask the user to re-enter
                LOGGER.log(Level.WARNING, "Choice must in [{0}-{1}], enter again !", new Object[]{min, max});
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Choice must in [{0}-{1}], enter again !", new Object[]{min, max});
            }
        }
    }

    /**
     * This method requires the user to enter a binary number from the keyboard
     *
     * @return string consisting of binary numbers
     */
    String getInputBinaryNumber() {
        while (true) {
            System.out.print("Input Binary Number: ");
            String binaryNumber = SCANNER.nextLine().trim();
            if (binaryNumber.matches("^[0-1]+$")) {
                return binaryNumber;
            } else {
                LOGGER.warning("Must enter 0 or 1. Enter again!");
            }
        }
    }

    /**
     * This method requires the user to enter an octal number from the keyboard
     *
     * @return string consisting of octal numbers
     */
    String getInputOctalNumber() {
        while (true) {
            System.out.print("Input Octal Number: ");
            String octalNumber = SCANNER.nextLine().trim();
            if (octalNumber.matches("^[0-7]+$")) {
                return octalNumber;
            } else {
                LOGGER.warning("Must be entered between 0-7. Enter again!");
            }
        }
    }

    /**
     * This method requires the user to enter a decimal number from the keyboard
     *
     * @return string consisting of decimal numbers
     */
    String getInputDecimalNumber() {
        while (true) {
            System.out.print("Input Decimal Number: ");
            String decimalNumber = SCANNER.nextLine().trim();
            if (decimalNumber.matches("^[0-9]+$")) {
                return decimalNumber;
            } else {
                LOGGER.warning("Must be entered between 0-9. Enter again!");
            }
        }
    }

    /**
     * This method requires the user to enter a hexadecimal number from the
     * keyboard
     *
     * @return string consisting of hexadecimal numbers
     */
    String getInputHexadecimalNumber() {
        while (true) {
            System.out.print("Input Hexadecimal Number: ");
            String hexadecimalNumber = SCANNER.nextLine().trim().toUpperCase();
            if (hexadecimalNumber.matches("^[0-9A-F]+$")) {
                return hexadecimalNumber;
            } else {
                LOGGER.warning("Must be entered between 0-9 A-F. Enter again!");
            }
        }
    }
}
