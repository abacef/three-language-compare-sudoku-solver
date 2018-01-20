public class jsolve {

    private int row;

    private int col;

    private int[][] board;

    private int[][] boardCpy;

    public jsolve(int[][] s) {
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
        int r = -1;
        int c = -1;
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
        long milCounter = 0;
        int[] s10 = {0, 0, 7, 0, 5, 0, 0, 9, 3};
        int[] s11 = {5, 8, 0, 7, 0, 0, 1, 6, 0};
        int[] s12 = {9, 0, 6, 8, 0, 3, 0, 2, 0};
        int[] s13 = {1, 0, 0, 0, 0, 0, 6, 0, 0};
        int[] s14 = {8, 6, 0, 0, 0, 0, 0, 7, 2};
        int[] s15 = {0, 0, 2, 0, 0, 0, 0, 0, 1};
        int[] s16 = {0, 5, 0, 3, 0, 9, 7, 0, 8};
        int[] s17 = {0, 2, 8, 0, 0, 4, 0, 1, 9};
        int[] s18 = {3, 9, 0, 0, 7, 0, 2, 0, 0};
        int[][] s1 = {s10, s11, s12, s13, s14, s15, s16, s17, s18};
        jsolve sea = new jsolve(s1);
        long start = System.nanoTime();
        long end = sea.backtrack() - start;
        sea.printBoard();   
        System.out.println("\n" + end);
        milCounter += end;

        int[] s20 = {0, 0, 4, 0, 3, 0, 8, 0, 0};
        int[] s21 = {0, 0, 0, 0, 0, 4, 5, 0, 0};
        int[] s22 = {2, 6, 0, 8, 0, 0, 3, 0, 0};
        int[] s23 = {4, 0, 0, 0, 7, 9, 0, 0, 6};
        int[] s24 = {3, 0, 0, 1, 4, 8, 0, 0, 7};
        int[] s25 = {7, 0, 0, 3, 6, 0, 0, 0, 8};
        int[] s26 = {0, 0, 9, 0, 0, 3, 0, 8, 5};
        int[] s27 = {0, 0, 1, 6, 0, 0, 0, 0, 0};
        int[] s28 = {0, 0, 3, 0, 2, 0, 6, 0, 0};
        int[][] s2 = {s20, s21, s22, s23, s24, s25, s26, s27, s28};
        jsolve smed = new jsolve(s2);
        start = System.nanoTime();
        end = smed.backtrack() - start;
        smed.printBoard();
        System.out.println("\n" + end);
        milCounter += end;

    }
}
