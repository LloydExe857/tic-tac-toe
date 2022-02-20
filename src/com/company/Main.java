package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Make it so that we can make inputs
        Scanner scanner = new Scanner(System.in);
        // This lets the user replay if they'd like
        String playAgain = "y";
        while (playAgain.toLowerCase(Locale.ROOT).equals("y")) {
            // Initialize the board
            Board gameBoard = new Board();
            // Keep track of the number of turns
            int turnNumber = 0;

            // We don't want the game to end until someone wins or every space is filled.
            while (!gameWon(gameBoard) && turnNumber < 9){
                // Determine whose turn it is
                String whoseTurn = decideTurn(turnNumber);
                System.out.println(whoseTurn + "'s turn.");
                // Display the current board
                gameBoard.displayBoard();

                boolean isValidSpace = false;
                int playingSpace = 0;
                while (!isValidSpace) {
                    System.out.print("\nPick an empty space 1-9. ");
                    playingSpace = Integer.parseInt(scanner.next()) - 1;
                    isValidSpace = checkValidity(gameBoard, playingSpace);
                    if (!isValidSpace) {
                        System.out.println("You cannot play there.");
                    }
                }
                gameBoard.spaces[playingSpace].setSpaceValue(whoseTurn);
                turnNumber++;
            }

            if (turnNumber >= 9)
                System.out.println("\nIt's a tie!");
            else
                System.out.println("\n" + decideTurn(turnNumber - 1) + " wins!");
            gameBoard.displayBoard();
            System.out.print("Would you like to play again? (y/n) ");
            playAgain = scanner.next();

        }
    }

    // Checks if a chosen space is valid to play on
    private static boolean checkValidity(Board gameBoard, int playingSpace) {
        if (playingSpace < 9 && playingSpace >= 0)
            return !gameBoard.spaces[playingSpace].getSpaceValue().equals("X") && !gameBoard.spaces[playingSpace].getSpaceValue().equals("O");
        return false;
    }

    // Calculate whose turn it is
    private static String decideTurn(int turnNumber) {
        String whoseTurn;
        if (turnNumber % 2 == 0){
            whoseTurn = "X";
        }
        else{
            whoseTurn = "O";
        }
        return whoseTurn;
    }

    // Determine if someone has won
    public static boolean gameWon(Board gameBoard){
        // Let's make it easier to read these values
        String[] spaceValues = new String[9];
        for (int i = 0; i < 9; i++){
            spaceValues[i] = gameBoard.spaces[i].getSpaceValue();
        }
        // Check if the rows are matching
        for (int i = 0; i < 9; i += 3){
            if (spaceValues[i].equals(spaceValues[i + 1]) && spaceValues[i].equals(spaceValues[i + 2])){
                return true;
            }
        }
        // Check if the columns match
        for (int i = 0; i < 3; i++){
            if (spaceValues[i].equals(spaceValues[i + 3]) && spaceValues[i].equals(spaceValues[i + 6])){
                return true;
            }
        }
        // Check for diagonals
        return (spaceValues[0].equals(spaceValues[4]) && spaceValues[0].equals(spaceValues[8])) || (spaceValues[2].equals(spaceValues[4]) && spaceValues[2].equals(spaceValues[6]));
    }
}
