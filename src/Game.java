/**
    This class sets up the board instances and player instances needed for a game. It receives input from the user to create the game. Such inputs include, the size of the board to create, the number of players, etc. 
    It has functionality for asking the players to take turns to play. It lets a player retry making a move if they make an invalid move during their turn. It also track if the game is over and prints a winner if there is one. 

*/

// we'll get to this class much later

import java.util.Scanner;

public class Game {
    private Board board;
    private Player[] players;
    private Scanner scanner = new Scanner(System.in);

    public Game() {
        // The Game constructor might initialize some default values if needed
    }

    public void setUpGame() {
        // Ask for the number of players
        System.out.print("Enter the number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Invalid number of players. Please enter a number between 2 and 4.");
            numPlayers = scanner.nextInt();
        }

        // Initialize the players
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String playerName = scanner.next();
            // more logic for unique names
            players[i] = new Player(playerName, i + 1);
        }

        // Ask for the size of the board and create it
        System.out.print("Enter the number of rows (4-10): ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns (4-10): ");
        int cols = scanner.nextInt();
        board = new Board(rows, cols);
    }

    public void printWinner(Player player) {
        System.out.println(player.getName() + " has won the game!");
    }

    public void playerTurn(Player currentPlayer) {
        // Handles the logic for a player's turn
        boolean validMove = false;
        while (!validMove) {
            try {
                int column = currentPlayer.makeMove();
                board.addToken(column, (char) ('0' + currentPlayer.getNumber())); // getNumber() should return an int
                validMove = true;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void play() {
        // Controls the overall flow of the game
        boolean gameWon = false;
        while (!board.boardFull() && !gameWon) {
            for (Player currentPlayer : players) {
                board.printBoard();
                playerTurn(currentPlayer);
                if (board.checkIfPlayerIsTheWinner((char) ('0' + currentPlayer.getNumber()))) {
                    printWinner(currentPlayer);
                    gameWon = true;
                    break; // Breaks out of the for loop, as we have a winner
                }
                // After each player's turn, check if the board is full
                if (board.boardFull()) {
                    System.out.println("The game ended in a draw.");
                    return; // Exits the play method, as the game is over
                }
            }
        }
        // If the loop exited naturally and no one won, it's a draw
        if (!gameWon) {
            System.out.println("The game ended in a draw.");
        }
    }

}
