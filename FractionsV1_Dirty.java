/**
 * William Humarang
 * CSS 143A
 * Assignment: FractionsV1
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FractionsV1_Dirty {
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
         * for 
         */
        String currFractionString = "";
        boolean fractionFound;
        int counter = 0; //counts the number of unique fractions

        try {
            input = new Scanner(new FileInputStream("fractions1.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("\"fractions.txt\" not found.");
            System.out.println("Exiting program...");
            System.exit(0);
        }

        
        //int increment = 0; //later used to increment arrays
        /**
         * Main while-loop that inputs another line in fractions.txt while
         * there is another fraction to input.
         */
        while(input.hasNextLine()) {
            int increment = 0;
            fractionFound = false;
            currFractionString = input.nextLine();
            currFractionArray = convertToIntArray(currFractionString);

            if (hasZeroDenom(currFractionArray)) {
                System.out.println(currFractionString + " has a zero denominator.");
                System.out.println("Skipping fraction...");
                continue;
            }

            //Used to put the first valid fraction into the large fraction array
            //i = fraction number

            /*if (!(initializedFirstFrac)) {
                numeratorArray[0] = currFractionArray[0];
                denominatorArray[0] = currFractionArray[1];
                initializedFirstFrac = true;
                fracCountArray[0] = 1;
                counter++;
                continue;
            }*/
            
            for (; increment < counter; increment++) {
                //the 0/0 thing could be due to numerator and denominator arrays not having anything to compare with
                if (!(isUniqueFraction(currFractionArray, numeratorArray, denominatorArray, increment))) {
                    System.out.println(currFractionArray[0] + " " + currFractionArray[1] + " " + numeratorArray[increment] + " " + denominatorArray[increment]);
                    fracCountArray[increment]++;
                    System.out.println("this means it's running through if");
                    fractionFound = true;
                    //break;
                    
                } else {

                    /*System.out.println("this means it's running through else");
                    System.out.println("increment value: " + increment);
                    numeratorArray[increment] = currFractionArray[0];
                    denominatorArray[increment] = currFractionArray[1];
                    fracCountArray[increment]++;
                    counter++;
                    //System.out.println(numeratorArray[0] + " " + denominatorArray[0]);
                    //System.out.println(numeratorArray[increment] + " " + denominatorArray[increment]);*/
                    
                }
                //System.out.println("increment value: " + increment);
            }
                if (!(fractionFound)) {
                    System.out.println("this means it's running through else");
                    System.out.println("increment value: " + increment);
                    numeratorArray[increment] = currFractionArray[0];
                    denominatorArray[increment] = currFractionArray[1];
                    fracCountArray[increment]++;
                    counter++;
                }
                
        }

        /**
         * A for-loop that would print each fraction with their corresponding count.
         * Precondition: Assumes that numerator, denominator, and fracCount arrays
         * are aligned in order
         */
        System.out.println("Counter: " + counter);
        for (int i = 0; i < counter; i++) {
            System.out.println(numeratorArray[i] + "/" + denominatorArray[i] + " has a count of " + fracCountArray[i]);
        }

        
        //System.out.println(numeratorArray[0] + " " + denominatorArray[0]);
        

        /*int[] testArray = {800, 900, 8, 10};
        System.out.println(isUniqueFraction(testArray));

        for (int i = 0; i < 4; i++) {
            System.out.println(crossProductArray[i]);
        }*/
    }

    public static void countFractions() {
        Scanner checkFractions = null;
        int tempFracCount = 0;

        try {
            checkFractions = new Scanner(new FileInputStream("fractions1.txt"));
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
     * array, and an int index for the fraction.
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
     * the 1 index is the denominator.
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
