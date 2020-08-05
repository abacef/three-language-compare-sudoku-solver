import java.lang.Math;

public class Jsolve {

    private int row;

    private int col;

    private final int[][] board;

    private final int[][] boardCpy;

    public Jsolve(int[][] s) {
        row = -1;
        col = 8;
        board = s;
        boardCpy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, boardCpy[i], 0, 9);
        }
    }

    private void printBoard() {
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
    }

    private boolean checkSquare() {
        int r;
        int c;
        if (row < 3) {
            r = 0;
        }
        else if (row < 6) {
            r = 3;
        }
        else {
            r = 6;
        }

        if (col < 3) {
            c = 0;
        }
        else if (col < 6) {
            c = 3;
        }
        else {
            c = 6;
        }

        int num = board[row][col];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (((i + r) != row || (j + c) != col) &&
                        board[i + r][j + c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid() {
        int num = board[row][col];
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == num) {
                return false;
            }
            if (i != row && board[i][col] == num) {
                return false;
            }
        }
        return checkSquare();
    }

    private void deadvance() {
        if (col == 0) {
            if (row != 0) {
                col = 8;
                row--;
            }
        }
        else {
            col--;
        }
        if (!(col == 0 && row == 0)) {
            if (boardCpy[row][col] != 0) {
                deadvance();
            }
        }
    }

    private void advance() {
        if (col == 8) {
            col = 0;
            row++;
        }
        else {
            col++;
        }
        if (!(row == 9 && col == 0)) {
            if (boardCpy[row][col] != 0) {
                advance();
            }
        }
    }

    private long backtrack() {
        advance();
        if (row == 9 && col == 0) {
            return System.nanoTime();
        }
        else {
            for (int i = 1; i < 10; i++) {
                board[row][col] = i;
                if (isValid()) {
                    long num = backtrack();
                    if (num != 0) {
                        return num;
                    }
                }
            }
            board[row][col] = 0;
            deadvance();
        }
        return 0;
    }

    public static void main(String[] args) {
        double milCounter = 0;

        final int[][][] boards = new int[][][] {
                {
                        {0, 0, 7, 0, 5, 0, 0, 9, 3},
                        {5, 8, 0, 7, 0, 0, 1, 6, 0},
                        {9, 0, 6, 8, 0, 3, 0, 2, 0},
                        {1, 0, 0, 0, 0, 0, 6, 0, 0},
                        {8, 6, 0, 0, 0, 0, 0, 7, 2},
                        {0, 0, 2, 0, 0, 0, 0, 0, 1},
                        {0, 5, 0, 3, 0, 9, 7, 0, 8},
                        {0, 2, 8, 0, 0, 4, 0, 1, 9},
                        {3, 9, 0, 0, 7, 0, 2, 0, 0}
                },
                {
                        {0, 0, 4, 0, 3, 0, 8, 0, 0},
                        {0, 0, 0, 0, 0, 4, 5, 0, 0},
                        {2, 6, 0, 8, 0, 0, 3, 0, 0},
                        {4, 0, 0, 0, 7, 9, 0, 0, 6},
                        {3, 0, 0, 1, 4, 8, 0, 0, 7},
                        {7, 0, 0, 3, 6, 0, 0, 0, 8},
                        {0, 0, 9, 0, 0, 3, 0, 8, 5},
                        {0, 0, 1, 6, 0, 0, 0, 0, 0},
                        {0, 0, 3, 0, 2, 0, 6, 0, 0},
                },
                {
                        {0, 0, 1, 0, 0, 0, 0, 2, 4},
                        {7, 0, 0, 6, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 9, 0, 0, 8},
                        {6, 0, 0, 0, 0, 0, 2, 8, 1},
                        {0, 4, 0, 0, 5, 0, 0, 3, 0},
                        {1, 7, 3, 0, 0, 0, 0, 0, 6},
                        {5, 0, 0, 3, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 4, 5, 0, 0, 2},
                        {4, 6, 0, 0, 0, 0, 5, 0, 0},
                },
                {
                        {0, 2, 0, 7, 0, 0, 3, 0, 0},
                        {5, 0, 0, 0, 0, 4, 0, 6, 0},
                        {0, 0, 6, 1, 3, 0, 0, 0, 0},
                        {0, 0, 2, 0, 6, 0, 0, 0, 4},
                        {8, 0, 0, 0, 0, 0, 0, 0, 5},
                        {4, 0, 0, 0, 1, 0, 8, 0, 0},
                        {0, 0, 0, 0, 8, 7, 1, 0, 0},
                        {0, 8, 0, 5, 0, 0, 0, 0, 7},
                        {0, 0, 7, 0, 0, 2, 0, 9, 0},
                }
        };

        for (int[][] board_state : boards) {
            Jsolve board = new Jsolve(board_state);
            long start = System.nanoTime();
            long end = board.backtrack() - start;
            board.printBoard();
            System.out.printf("\n%.3f milliseconds\n", end * Math.pow(10, -6));
            milCounter += end;
        }

        System.out.printf("\n%.3f milliseconds total\n", milCounter * Math.pow(10, -6));
    }
}