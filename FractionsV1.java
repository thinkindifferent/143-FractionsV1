/**
 * William Humarang
 * CSS 143A
 * Assignment: FractionsV1
 */

 //Imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FractionsV1 {

    //Instance variables for the program.
    static Scanner input = null;
    static int fracCount;
    public static void main(String[] args) {

        /**
         * An instance of countFractions() is used to count the number
         * of fractions in the txt file, with the assumption that there
         * is one fraction per line.
         */
        countFractions();

        /**
         * Arrays to store all of the data, along with a temporary array
         * to store the current fraction's numbers.
         */
        int[] fracCountArray = new int[fracCount];
        int[] currFractionArray = new int[2];
        
        int[] numeratorArray = new int[fracCount];
        int[] denominatorArray = new int[fracCount];

        /**
         * Placeholder string for the current inputted fraction, a boolean
         * to represent an inputted fraction matching a fraction from the arrays,
         * and a counter to count the number of unique fractions (to prevent
         * any out of bounds exceptions).
         */
        String currFractionString = "";
        boolean fractionFound;
        int counter = 0; 

        //try-catch to open the file fractions.txt
        try {
            input = new Scanner(new FileInputStream("fractions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("\"fractions.txt\" not found.");
            System.out.println("Exiting program...");
            System.exit(0);
        }

        /**
         * Main while-loop that inputs another line in fractions.txt while
         * there is another fraction to input.
         */
        while(input.hasNextLine()) {
            int increment = 0;
            fractionFound = false;
            currFractionString = input.nextLine();
            currFractionArray = convertToIntArray(currFractionString);

            //Checks if any fractions in the txt file has a zero denominator.
            if (hasZeroDenom(currFractionArray)) {
                System.out.println(currFractionString + " has a zero denominator.");
                System.out.println("Skipping fraction...");
                continue;
            }
            
            /**
             * for loop that checks the current fraction with the fractions
             * stored inside the arrays. If it finds a match, sets fractionFound
             * to true and increments the count array at the current index.
             */
            for (; increment < counter; increment++) {
                if (!(isUniqueFraction(currFractionArray, numeratorArray, denominatorArray, increment))) {
                    fracCountArray[increment]++;
                    fractionFound = true;
                }
            }

            /**
             * If a fraction doesn't get matched, the fraction will be stored
             * in the numerator and denominator arrays, while also incrementing
             * the array fracCount and the counter variable.
             */
            if (!(fractionFound)) {
                numeratorArray[increment] = currFractionArray[0];
                denominatorArray[increment] = currFractionArray[1];
                fracCountArray[increment]++;
                counter++;
            }
                
        }

        /**
         * A for-loop that would print each fraction with their corresponding count.
         * Assumes that numerator, denominator, and fracCount arrays
         * are aligned in order.
         */
        System.out.println("Counter: " + counter);
        for (int i = 0; i < counter; i++) {
            System.out.println(numeratorArray[i] + "/" + denominatorArray[i] + " has a count of " + fracCountArray[i]);
        }
    }

    /**
     * Method that counts the number of fractions inside the text file, with
     * the assumption that every line in the file has a fraction and no
     * extraneous characters
     */
    public static void countFractions() {
        Scanner checkFractions = null;
        int tempFracCount = 0;

        //Opens the file
        try {
            checkFractions = new Scanner(new FileInputStream("fractions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("\"fractions.txt\" not found.");
            System.out.println("Exiting program...");
            System.exit(0);
        }

        while(checkFractions.hasNextLine()) {
            checkFractions.nextLine();
            tempFracCount++;
        }
        checkFractions.close();

        fracCount = tempFracCount;
    }

    /**
     * Expects 3 arrays: the current fraction, numerator array, denominator
     * array, and an int index for the fraction. Tests for whether or not
     * currFraction is unique using a cross product.
     * @param fractionArray
     * @return
     */
    public static boolean isUniqueFraction(int[] currFraction, int[] numerator, int[] denominator, int index) {
        int outsideProduct = currFraction[0] * denominator[index];
        int insideProduct = currFraction[1] * numerator[index];

        if (outsideProduct == insideProduct) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Expects a 2 index int array where the 0 index is the numerator and
     * the 1 index is the denominator. Checks for whether or not the
     * fraction has a 0 denominator.
     * @param fractionArray
     * @return
     */
    public static boolean hasZeroDenom(int[] fractionArray) {
        if (fractionArray[1] == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adapted from https://www.geeksforgeeks.org/split-string-java-examples/
     * Converts a fraction string in the form "(numerator)/(denominator)" to
     * a 2 index int array, with numerator at index 0 and denominator at
     * index 1.
     * @param fracString
     * @return
     */
    public static int[] convertToIntArray(String fracString) {
        String[] fracStringArray = fracString.split("/", 2);
        int[] retArray = new int[2];

        for(int i = 0; i < 2; i++) {
            retArray[i] = Integer.parseInt(fracStringArray[i]);
        }

        return retArray;
    }
}