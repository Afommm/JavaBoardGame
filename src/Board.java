/**
 A class that would mimic an n by m board
 where n is the number of rows, and m is the number of columns.
 This class would handle creation of the board, adding a token to the board, printing the current state of the board, checking if the board is full, checking if the current player has just played a winning move. 
 */
 
// we'll get to this class much later

public class Board {
    private char[][] board;
    private int rows;
    private int columns;

    // Constructor to initialize the board
    public Board(int rows, int columns) {
        if (rows < 4 || rows > 10 || columns < 4 || columns > 10) {
            throw new IllegalArgumentException("Rows and columns must be between 4 and 10.");
        }
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
        boardSetUp();
    }

    // Initializes the board with empty spaces
    private void boardSetUp() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Prints the current state of the board
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Checks if a column is full
    public boolean columnFull(int col) {
        return board[0][col] != '-';
    }

    // Checks if the board is full
    public boolean boardFull() {
        for (int j = 0; j < columns; j++) {
            if (!columnFull(j)) {
                return false;
            }
        }
        return true;
    }

    // Adds a token to the board
    public void addToken(int col, char token) {
        if (col < 0 || col >= columns) {
            throw new IllegalArgumentException("Column number is out of bounds.");
        }
        if (columnFull(col)) {
            throw new IllegalStateException("Column is full.");
        }
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][col] == '-') {
                board[i][col] = token;
                break;
            }
        }
    }

    public boolean checkVertical(char playerToken) {
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows - 3; row++) {
                if (board[row][col] == playerToken &&
                    board[row + 1][col] == playerToken &&
                    board[row + 2][col] == playerToken &&
                    board[row + 3][col] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(char playerToken) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == playerToken &&
                    board[row][col + 1] == playerToken &&
                    board[row][col + 2] == playerToken &&
                    board[row][col + 3] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRightDiagonal(char playerToken) {
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == playerToken &&
                    board[row + 1][col + 1] == playerToken &&
                    board[row + 2][col + 2] == playerToken &&
                    board[row + 3][col + 3] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLeftDiagonal(char playerToken) {
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 3; col < columns; col++) {
                if (board[row][col] == playerToken &&
                    board[row + 1][col - 1] == playerToken &&
                    board[row + 2][col - 2] == playerToken &&
                    board[row + 3][col - 3] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    // Placeholder methods for checking winning conditions
    public boolean checkIfPlayerIsTheWinner(char playerToken) {
        return checkVertical(playerToken) || checkHorizontal(playerToken) ||
            checkRightDiagonal(playerToken) || checkLeftDiagonal(playerToken);
    }


}
