import java.util.Scanner;

public class TicTacToe {
    private final int BOARDSIZE = 3;
    private enum Status {WIN, DRAW, CONTINUE}
    private char[][] board = new char[BOARDSIZE][BOARDSIZE];
    private boolean firstPlayer = true;
    private boolean gameOver = false;

    public void play(){
        Scanner scanner = new Scanner(System.in);
        board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        printBoard();

        while (!gameOver) {
            int row, col;
            printStatus(firstPlayer ? 1 : 2);

            while (true) {
                while(true) {
                    System.out.print("Enter row (0-2): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter an integer");
                        scanner.nextLine();
                        continue;
                    }
                    row = scanner.nextInt();
                    break;
                }
                while(true) {
                    System.out.print("Enter column (0-2): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter an integer");
                        scanner.nextLine();
                        continue;
                    }
                    col = scanner.nextInt();
                    break;
                }


                if (validMove(row, col)) break;
                System.out.println("Invalid move, try again.");
            }

            char symbol = firstPlayer ? 'X' : 'O';
            board[row][col] = symbol;
            printBoard();

            Status status = gameStatus();
            if (status == Status.WIN) {
                System.out.println("Player " + (firstPlayer ? 1 : 2) + " wins!");
                gameOver = true;
            } else if (status == Status.DRAW) {
                System.out.println("Game is a draw!");
                gameOver = true;
            } else {
                firstPlayer = !firstPlayer; // switch turn
            }
        }
    }
    private void printStatus(int player){
        System.out.println("Player " + player + "'s turn (" + (player == 1 ? "X" : "O") + ")");
    }
    private Status gameStatus(){
        for (int i = 0; i < BOARDSIZE; i++) {
            if (board[i][0] != ' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                return Status.WIN;
            }
            if (board[0][i] != ' ' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {
                return Status.WIN;
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return Status.WIN;
        }
        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return Status.WIN;
        }

        // Check for draw or continue
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if (board[i][j] == ' ') return Status.CONTINUE;
            }
        }
        return Status.DRAW;
    }

    public void printBoard(){
        System.out.println("-------------");
        for (int i = 0; i < BOARDSIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARDSIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    /*
    private void printSymbol(int column, char value){
        //
    }
    */

    private boolean validMove(int row, int col) {
        if (row < 0 || row >= BOARDSIZE || col < 0 || col >= BOARDSIZE) return false;
        return board[row][col] == ' ';
    }

}