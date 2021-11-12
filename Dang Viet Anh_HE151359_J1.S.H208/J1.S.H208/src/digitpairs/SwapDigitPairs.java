/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.H208
 * Title: Swap Digits Pairs
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */

package digitpairs;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code SwapDigitPairs} class contains methods inputNumber, swapDigitPairs
 * and main that can be used to returns a new integer whose value is similar to
 * n's but with each pair of digits swapped in order
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Dang Viet Anh
 */
public class SwapDigitPairs {

    /**
     * declare a final variable LOGGER to use logging for {@code SwapDigitPairs}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(SwapDigitPairs.class.getName());

    /**
     * This method will ask the user to input from the keyboard a integer until
     * number is a positive.
     *
     * @return a positive number
     */
    private int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter number: ");
                int inputNumber = Integer.parseInt(scanner.nextLine());
                if (inputNumber > 0) {
                    // check if inputNumber > 0 then return
                    return inputNumber;
                } else {
                    // if inputNumber<0 then go back and re-enter
                    LOGGER.warning("Number must is positive, enter again: ");
                }
            } catch (NumberFormatException e) {
                // if inputNumber is not a valid number then go back and re-enter
                LOGGER.warning("Not valid number, enter again: ");
            }
        }
    }

    /**
     * This method returns a new integer whose value is similar to n's but with
     * each pair of digits swapped in order.
     * <p>
     * For example, the call of swapDigitPairs(482596) would return 845269.
     *
     * @param number a integer number
     */
    private int swapDigitPairs(int number) {
        int result = 0; //  number after swap digit pairs
        int place = 1;  //  the position of a digit in a number
        while (number > 9) { // terminating condition is at least two digits left
            result += place * 10 * (number % 10);   // swap the last digit to the tenth place
            number /= 10;   // divide the number by 10 to truncate the number
            result += place * (number % 10);    // swap the tenth digit to the single digit place
            number /= 10; // divide the number by 10 to truncate the number
            place *= 100; // similarly multiply by 100 to deals with two digits per iteration
        }
        return result + (place * number);
    }

    /**
     * This is the place to run program
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwapDigitPairs swapDigitPairs = new SwapDigitPairs();
        int input = swapDigitPairs.inputNumber(); // input integer
        LOGGER.log(Level.INFO, "Swap digit: {0,number,#}", swapDigitPairs.swapDigitPairs(input)); // call swapDigitPairs
    }
}
