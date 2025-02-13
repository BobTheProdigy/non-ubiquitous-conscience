/*
 * Author: William Corkey & Jackson Sabo
 * Date: 12/3/24
 * File Name: Main.java
 * Purpose: This program is the game Mastermind, and this class is the overwatch keeping track of turns and taking care of printing result
 */

import java.util.Scanner;

public class Main {
    // print starting text
    // asks if cheats
    // create new board
    // call take turn method within board class 10 times or until win
    // print ending result
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the greatest game of Mastermind to exist!\nYou have 10 guesses to break the code.");
        System.out.println("\nfeedback is only provided if you have a hit or a partial. It is not necessarily in the order of the pegs");
        System.out.println("h means hit -> a peg had the correct color in the correct location");
        System.out.println("p means partial -> a peg had the correct color in the incorrect location\n");
        System.out.println("Would you like cheats? (y/n)");
        boolean cheats = scan.nextLine().equals("y");
        Board gameBoard = new Board(cheats);
        boolean win = false;
        int turn = 0;
        while (!win && turn <= 9) {
            turn++;
            System.out.println("\nturn " + turn);
            String result = gameBoard.takeTurn();
            if (result.length() == 4 && result.equals("hhhh")) {
                win = true;
            }
        }
        scan.close();
        if (win) {System.out.println("You win after " + turn + " guesses!");}
        else {
            System.out.println("You lose! You have spent all 10 of your turns :(\nThe correct answer was ");
            System.out.println(gameBoard.getAnswer());
        }
    }
}