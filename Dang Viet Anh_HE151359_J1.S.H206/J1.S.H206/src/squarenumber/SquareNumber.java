/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.H206
 * Title: Square Number
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */
package squarenumber;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The {@code SquareNumber} class contains methods getInputNumber, printSquare
 * that can be used to prints the numbers in the range from min to max inclusive
 * in a square pattern.
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Dang Viet Anh
 */
public class SquareNumber {

    /**
     * declare a final variable LOGGER to use logging for {@code SquareNumber}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(SquareNumber.class.getName());

    /**
     * This method requires the user to enter an integer keyboard
     *
     * @param message (message for input)
     * @return integer number
     */
    private int getInputNumber(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message); // notify user input
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // if the input is not numeric then ask user to re-enter
                LOGGER.warning("Not valid number, enter again: ");
            }
        }
    }

    /**
     * This method prints the numbers in the range from min number to max number
     * inclusive in a square pattern.
     *
     */
    private void printSquare() {
        int minNumber = getInputNumber("Enter min number: "); // enter min number from keyboard
        int maxNumber;
        while (true) {
            maxNumber = getInputNumber("Enter max number: "); // enter max number from keyboard
            if (minNumber < maxNumber) {
                // if maxNumber > minNumber then break
                break;
            } else {
                // if the maxNumber < minNumber ask user to re-enter maxNumber
                LOGGER.warning("Max number must be greater than min number. Enter again!");
            }
        }
        for (int i = minNumber; i <= maxNumber; i++) {
            for (int j = i; j <= maxNumber; j++) {
                // print from number i to maxNumber 
                System.out.print(j);
            }
            for (int j = minNumber; j < i; j++) {
                // print from minNumber to i
                System.out.print(j);
            }
            System.out.println("");
            // print newline after each loop from minNumber to maxNumber
        }
    }

    /**
     * This is a main method have an Object to call SquareNumber methods and run
     * the program.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SquareNumber squareNumber = new SquareNumber();
        squareNumber.printSquare(); // enter min and max number and call printSquare(min,max)
    }

}
