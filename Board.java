/*
 * Author: William Corkey & Jackson Sabo
 * Date: 12/3/24
 * File Name: Board.java
 * Purpose: Takes care of handling the board of guesses, and getting the guesses. Handles everything shy of counting turns and printing result.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    // fields
    // board is an arraylist of all the guesses the user makes, used for printing history
    private ArrayList<Row> board = new ArrayList<>();
    // result is returned to be checked and printed in history
    private String result;
    // used for comparing each row to get result per turn
    private final Row answer = new Row();
    // allows user to see answer each turn
    private final boolean cheats;

    // Name: Custom Board Constructor
    // Purpose: overload constructor, which has logic for if cheats or not
    // Parameters: boolean whether user wants to cheat or not
    // Return values: nothing as its a constructor
    public Board(boolean cheatsInput) {
        cheats = cheatsInput;
        if (cheats) {
            System.out.print("Who said cheaters never prosper?");
        }
    }

    // Name: getAnswer
    // Purpose: return the row object of answer to the user, to allow for printing every turn
    // Parameters: none
    // Return values: Answer row object
    public Row getAnswer() {
        return answer;
    }

    // Name: takeTurn
    // Purpose: prints user the answer if cheats, takes a turn until successful input is received, adds to board and prints
    // if user input is history, it prints out the history for the user
    // Parameters: none
    // Return value: String of hits and misses for the main method to print.
    // *** This method exceeds the 30 line limit primarily because of my shenanigans.
    // is is close to the limit because it contains functionality for all of checking input, and creating and printing result ***
    public String takeTurn() {
        String[] failedInputResponses = {"", "It is not that hard.", "How are you having this much trouble with it?", "My grandmother would have done this correctly before you.", "Ah, another bold attempt to redefine 'failure.' Impressive.", "At least you're consistent!", "Don't worry, practice makes... well, maybe progress?", "Ah, aiming for participation trophies again, I see.", "Hey, if at first you don't succeed, try delegating it to someone else.", "Does anybody know what time it is? I have a meeting at 2:00.", "I guess being successful is overrated anyway.", "I give up, I have better things to do."};
        int failedInputCounter = 0;
        boolean validInput = false;
        String stringResult = "";
        Scanner scan = new Scanner(System.in);
        while (!validInput) {
            System.out.println("Guess the colors as csv: (red, green, yellow, blue, purple, gray) or type \"history\"");
            if (cheats) {
                System.out.println("Answer: " + answer);
            }
            String userInput = scan.nextLine();
            if (userInput.equals("history")) {
                printHistory();
            }
            else {
                // fun little addition to insult the user
                if(failedInputCounter < failedInputResponses.length) {
                    System.out.println("\n" + failedInputResponses[failedInputCounter]);
                    failedInputCounter++;
                }
                // *** can't think of a way to avoid making a row class before knowing functional code as checkValidInput() lies within row class ***
                Row guess = new Row(userInput);
                validInput = guess.checkValidInput();
                if (validInput) {
                    System.out.println("guess: " + guess);
                    stringResult = guess.compare(answer.getRow());
                    board.add(guess);
                    // printing result
                    System.out.println("Result: " + stringResult);
                }
            }
        }
        this.result = stringResult;
        return stringResult;
    }

    // Name: printHistory
    // Purpose: prints some details about what each part means and then prints each guess from board object and its result
    // Parameters: none
    // Return value: none
    public void printHistory() {
        System.out.println("\nfeedback is only provided if you have a hit or a partial. It is not necessarily in the order of the pegs");
        System.out.println("h means hit -> a peg had the correct color in the correct location");
        System.out.println("p means partial -> a peg had the correct color in the incorrect location\n");
        for (int i = 0; i < board.size(); i++) {
            System.out.println("Guess: " + board.get(i) + "  Result: " + result);
        }
        System.out.println();
    }

    // Name: toString
    // Purpose: to print the entire board for the player to visualize
    // Parameters: none
    // Return values: String, which is a concatenation of answer row objects
    @Override
    public String toString() {
        String returnString = "";
        for (Row x : board) {
            returnString += x;
        }
        return returnString;
    }
}
