/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.H203
 * Title: Reverse Word
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */
package reversal;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code ReverseWord} class contains methods that can be used to prints the
 * words in opposite order.
 *
 * @author Dang Viet Anh
 * <p>
 */
public class ReverseWord {

    /**
     * declare a final variable LOGGER to use logging for {@code ReverseWord}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(ReverseWord.class.getName());

    /**
     * This method will take user input string from the keyboard until string is
     * not empty.
     *
     * @return string a valid input string
     */
    private String enterString() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter string (not empty or blank): ");
            String inputString = scanner.nextLine().trim();
            if (inputString.isEmpty()) {
                // if input is empty enter again 
                LOGGER.warning("Not empty, enter again: "); 
            } else {
                return inputString; // if input valid then return 
            }
        }
    }

    /**
     * This method will accept a string and return the words in opposite order
     *
     * @param string is an string parameter
     * @return a string containing the words in opposite order
     */
    private void printReverse(String string) {
        StringBuilder result = new StringBuilder();
        String words[] = string.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            if (i == words.length - 1) {
                //capitalize the first letter
                result.append((words[i].substring(0, 1)).toUpperCase().concat(words[i].substring(1, words[i].length()))).append(" ");
            } else if (i == 0) {
                //lowercase the first letter
                result.append((words[i].substring(0, 1)).toLowerCase().concat(words[i].substring(1, words[i].length()))).append(" ");
            } else {
                // If it is not last word or first word, add result
                result.append(words[i]).append(" ");
            }
        }
        LOGGER.log(Level.INFO, "String reverse is: {0}", result);
    }

    /**
     * This is place the program runs to get the string word reverse
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        ReverseWord reverseWord = new ReverseWord(); // initialize class ReverseWord to use
        String inputString = reverseWord.enterString(); // input string from keyboard
        reverseWord.printReverse(inputString); // call printReverse(input)
    }
}
