/*
 * Author: William Corkey & Jackson Sabo
 * Date: 12/3/24
 * File Name: Row.java
 * Purpose: Deals with individual rows, comparing results of rows, creating rows to make ArrayList in Board
 */

import java.util.ArrayList;
import java.util.Set;

public class Row {
    // fields
    // row as arraylist of pegs
    private ArrayList<Peg> row = new ArrayList<>(5);
    // valid colors -- Set is more efficient, I use the same logic below as if it were an arraylist
    private final Set<String> validColors = Set.of("red", "green", "yellow", "blue", "purple", "gray");

    // Name: Row
    // Purpose: Default row constructor, used for creating answer ArrayList of pegs with RNG corresponding to different color strings
    // Parameters: none
    // Return values: nothing as its a constructor
    public Row() {
        for (int i = 0; i < 4; i++) {
            int randResult = (int)(Math.random() * 6);
            switch (randResult) {
                case 0 -> row.add(new Peg("red"));
                case 1 -> row.add(new Peg("green"));
                case 2 -> row.add(new Peg("yellow"));
                case 3 -> row.add(new Peg("blue"));
                case 4 -> row.add(new Peg("purple"));
                case 5 -> row.add(new Peg("gray"));
            }
        }
    }

    // Name: Row
    // Purpose: Custom row constructor, used for creating row as ArrayList of pegs with user input
    // Parameters: String (user input of their guesses)
    // Return values: nothing as its a constructor
    public Row(String userInput) {
        String[] userInputList = userInput.split(",");
        for (String color : userInputList) {
            Peg temp = new Peg(color.strip());
            row.add(temp);
        }
    }

    // Name: getRow
    // Purpose: returns the row to the user, which is then printed using toString()
    // Parameters: none
    // Return values: row, which is an arraylist of peg objects
    public ArrayList<Peg> getRow() {
        return row;
    }
    
    // Name: compare
    // Purpose: compare the inputted answer row object to the row object of user guess, for hits and stands
    // Parameters: ArrayList of the answer peg objects
    // Return values: String of hits and partials, in that order
    // iterates through guess, first checking hits, then, if a non-matched index matches, mark as partial
    public String compare(ArrayList<Peg> answer) {
        // this method is only run in the guess object, as matched is checking for hits/partials in guess not answer
        String stringResult = "";
        boolean[] guessMatched = new boolean[4];
        boolean[] answerMatched = new boolean[4];
        // check for hits
        for (int i = 0; i < 4; i++) {
            if(row.get(i).compare(answer.get(i))) {
                stringResult += "h";
                guessMatched[i] = true;
                answerMatched[i] = true;
            }
        }
        // checking for partials, iterating through guesses and making sure guess not already matched
        for (int i = 0; i < 4; i++) {
            if(!guessMatched[i]) {
                // iterating through answer
                for (int j = 0; j < 4; j++) {
                    // checking answer not already matched, as it's not a partial if equal, setting indexes true if partial
                    if (!answerMatched[j] && row.get(i).compare(answer.get(j))) {
                        stringResult += "p";
                        guessMatched[i] = true;
                        answerMatched[j] = true;
                        break;
                    }
                }
            }
        }
        return stringResult;
    }

    // Name: checkValidInput
    // Purpose: check that user input is valid and give specific feedback if problem present
    // Parameters: none, used as a call within an object
    // Return values: boolean of whether input is valid
    // checks valid input for correct length (4) and valid colors from private set
    public boolean checkValidInput() {
        if (row.size() != 4) {
            System.out.println("Invalid input length. Must be four colors. Try again.");
            return false;
        }
        for (Peg color : row) {
            if (!validColors.contains(color.getColor())) {
                System.out.println(color.getColor() + " is not a valid color.");
                return false;
            }
        }
        return true;
    }
    
    // Name: toString
    // Purpose: return a row as a concatenation of strings
    // Parameters: none
    // Return values: String concatenation of pegs (colors) in a row
    @Override
    public String toString() {
        String returnString = "";
        for (Peg x : row) {
            returnString += x + " ";
            // System.out.println("returnString: " + returnString);
        }
        return returnString;
    }
}
