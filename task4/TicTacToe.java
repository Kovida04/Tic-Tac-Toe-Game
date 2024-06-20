import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the board
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if the board is full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if there is a win
    public boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check the rows for a win
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    // Check the columns for a win
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    // Check the diagonals for a win
    private boolean checkDiagonals() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) ||
                (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    // Check if all three values are the same (and not empty) indicating a win
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Change player marks back and forth
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Place a mark at the cell specified by row and col with the current player's mark
    public boolean placeMark(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayer;
                    return true;
                }
            }
        }
        return false;
    }

    // Main method to run the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean playAgain = true;

        while (playAgain) {
            boolean gameEnded = false;
            game.initializeBoard();
            game.currentPlayer = 'X';
            System.out.println("Tic-Tac-Toe!");
            game.printBoard();

            while (!gameEnded) {
                int row, col;

                // Get user input
                System.out.println("Player " + game.currentPlayer + ", enter your move (row and column: 0, 1, or 2): ");
                row = scanner.nextInt();
                col = scanner.nextInt();

                // Place the mark and check if the move is valid
                if (game.placeMark(row, col)) {
                    game.printBoard();

                    // Check for a win
                    if (game.checkWin()) {
                        System.out.println("Player " + game.currentPlayer + " wins!");
                        gameEnded = true;
                    } else if (game.isBoardFull()) {
                        System.out.println("The game is a draw!");
                        gameEnded = true;
                    } else {
                        game.changePlayer();
                    }
                } else {
                    System.out.println("This move is not valid.");
                }
            }

            // Ask if players want to play again
            System.out.println("Do you want to play again? (yes/no)");
            scanner.nextLine(); // Consume newline
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        scanner.close();
    }
}
