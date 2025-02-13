/*
 * Author: William Corkey & Jackson Sabo
 * Date: 12/3/24
 * File Name: Row.java
 * Purpose: Deals with individual pegs
 * 1) creates the colors as strings to print and compare
 * 2) compares and returns results of individual pegs
 * 3) creating pegs to make ArrayList in Row
 */

public class Peg {
    // fields
    // color -- String for checking for valid input
    // peg -- String for comparing guess and answer, and printing colors to console
    private final String peg;
    private final String color;

    // Name: Peg
    // Purpose: Overload constructor to assign string inputs of colors to their corresponding ANSI codes with switch case
    // Parameters: String of color choice
    // Return values: nothing, it's a constructor
    // would be inefficient to make variables for every string if only one ends up being used, so switch case is better
    public Peg(String userInput) {
        color = userInput;
        switch (userInput) {
            case "red" -> peg = "\u001B[31m\u25A0\u001B[0m";
            case "green" -> peg = "\u001B[32m\u25A0\u001B[0m";
            case "yellow" -> peg = "\u001B[33m\u25A0\u001B[0m";
            case "blue" -> peg = "\u001B[34m\u25A0\u001B[0m";
            case "purple" -> peg = "\u001B[35m\u25A0\u001B[0m";
            // had to adjust my gray code to match with my IDE :)
            case "gray" -> peg = "\u001B[2m\u25A0\u001B[0m";
            default -> peg = "what the fuck?";
        }
    }


    // Name: getColor
    // Purpose: allow for obtaining variable without possible manipulation
    // Parameters: none
    // Return values: String color, which is the inputted string
    public String getColor() {
        return color;
    }

    // Name: compare
    // Purpose: compare the string color of the pegs
    // Parameters: other peg object that's being compared
    // Return values: true or false of whether colors equal each other
    public boolean compare(Peg otherColor) {
        return color.equals(otherColor.getColor());
    }

    // Name: toString
    // Purpose: return the peg as the ANSI color to be printed to the user
    // Parameters: none
    // Return values: String as ANSI code for the color's square
    @Override
    public String toString() {
        return peg;
    }
}
