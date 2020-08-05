#include <math.h> // pow
#include <stdio.h> // printf
#include <stdlib.h> // malloc
#include <stdbool.h> // the usual suspects
#include <sys/time.h> // time

typedef struct {
    int row;
    int col;
    int board[9][9];
    int board_cpy[9][9];
} psolve_packet;

psolve_packet *psolve(int incoming_board[9][9]) {
    psolve_packet *pack = malloc(sizeof(psolve_packet));
    pack->row = -1;
    pack->col = 8;
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            pack->board[i][j] = incoming_board[i][j];
            pack->board_cpy[i][j] = incoming_board[i][j];
        }
    }
    return pack;
}

void print_board(psolve_packet *pack) {
    for (int i = 0; i < 9; i++) {
        putchar('\n');
        for (int j = 0; j < 9; j++) {
            printf("%d ", pack->board[i][j]);
        }
    }
}

bool checkSquare(psolve_packet *pack) {
    int r = -1;
    int c = -1;
    if (pack->row < 3) {
        r = 0;
    }
    else if (pack->row < 6) {
        r = 3;
    }
    else {
        r = 6;
    }

    if (pack->col < 3) {
        c = 0;
    }
    else if (pack->col < 6) {
        c = 3;
    }
    else {
        c = 6;
    }

    int num = pack->board[pack->row][pack->col];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (((i + r) != pack->row || (j + c) != pack->col) &&
                    pack->board[i + r][j + c] == num) {
                return false;
            }
        }
    }
    return true;
}

bool is_valid(psolve_packet *pack) {
    int num = pack->board[pack->row][pack->col];
    for (int i = 0; i < 9; i++) {
        if (i != pack->col && pack->board[pack->row][i] == num) {
            return false;
        }
        if (i != pack->row && pack->board[i][pack->col] == num) {
            return false;
        }
    }
    return checkSquare(pack);
}

void deadvance(psolve_packet *pack) {
    if (pack->col == 0) {
        if (pack->row != 0) {
            pack->col = 8;
            pack->row--;
        }
    }
    else {
        pack->col--;
    }
    if (!(pack->col == 0 && pack->row == 0)) {
        if (pack->board_cpy[pack->row][pack->col] != 0) {
            deadvance(pack);
        }
    }
}

void advance(psolve_packet *pack) {
    if (pack->col == 8) {
        pack->col = 0;
        pack->row++;
    }
    else {
        pack->col++;
    }
    if (!(pack->row == 9 && pack->col == 0)) {
        if (pack->board_cpy[pack->row][pack->col] != 0) {
            advance(pack);
        }
    }
}

suseconds_t backtrack(psolve_packet *pack) {
    advance(pack);
    if (pack->row == 9 && pack->col == 0) {
        struct timeval t2;
        gettimeofday(&t2, '\0');
        return t2.tv_usec;
    }
    else {
        for (int i = 1; i < 10; i++) {
            pack->board[pack->row][pack->col] = i;
            if (is_valid(pack)) {
                long num = backtrack(pack);
                if (num != 0) {
                    return num;
                }
            }
        }
        pack->board[pack->row][pack->col] = 0;
        deadvance(pack);
    }
    return 0;
}

int main() {
    double mil_counter = 0;

    int s1[9][9] = {
        {0, 0, 7, 0, 5, 0, 0, 9, 3},
        {5, 8, 0, 7, 0, 0, 1, 6, 0},
        {9, 0, 6, 8, 0, 3, 0, 2, 0},
        {1, 0, 0, 0, 0, 0, 6, 0, 0},
        {8, 6, 0, 0, 0, 0, 0, 7, 2},
        {0, 0, 2, 0, 0, 0, 0, 0, 1},
        {0, 5, 0, 3, 0, 9, 7, 0, 8},
        {0, 2, 8, 0, 0, 4, 0, 1, 9},
        {3, 9, 0, 0, 7, 0, 2, 0, 0}
    };
    psolve_packet *sea = psolve(s1);
    struct timeval t1;
    gettimeofday(&t1, '\0');
    double end = (backtrack(sea) - t1.tv_usec);
    print_board(sea);
    free(sea);
    printf("\n%.3lf milliseconds\n", end * pow(10, -3));
    mil_counter += end;

    int s2[9][9] = {
        {0, 0, 4, 0, 3, 0, 8, 0, 0},
        {0, 0, 0, 0, 0, 4, 5, 0, 0},
        {2, 6, 0, 8, 0, 0, 3, 0, 0},
        {4, 0, 0, 0, 7, 9, 0, 0, 6},
        {3, 0, 0, 1, 4, 8, 0, 0, 7},
        {7, 0, 0, 3, 6, 0, 0, 0, 8},
        {0, 0, 9, 0, 0, 3, 0, 8, 5},
        {0, 0, 1, 6, 0, 0, 0, 0, 0},
        {0, 0, 3, 0, 2, 0, 6, 0, 0},
    };
    psolve_packet *smed = psolve(s2);
    gettimeofday(&t1, '\0');
    end = (backtrack(smed) - t1.tv_usec);
    print_board(smed);
    free(smed);
    printf("\n%.3lf milliseconds\n", end * pow(10, -3));
    mil_counter += end;

    int s3[9][9] = {
        {0, 0, 1, 0, 0, 0, 0, 2, 4},
        {7, 0, 0, 6, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 9, 0, 0, 8},
        {6, 0, 0, 0, 0, 0, 2, 8, 1},
        {0, 4, 0, 0, 5, 0, 0, 3, 0},
        {1, 7, 3, 0, 0, 0, 0, 0, 6},
        {5, 0, 0, 3, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 0, 0, 2},
        {4, 6, 0, 0, 0, 0, 5, 0, 0}
    };
    psolve_packet *shar = psolve(s3);
    gettimeofday(&t1, '\0');
    end = (backtrack(shar) - t1.tv_usec);
    print_board(shar);
    free(shar);
    printf("\n%.3lf milliseconds\n", end * pow(10, -3));
    mil_counter += end;

    int s4[9][9] = {
        {0, 2, 0, 7, 0, 0, 3, 0, 0},
        {5, 0, 0, 0, 0, 4, 0, 6, 0},
        {0, 0, 6, 1, 3, 0, 0, 0, 0},
        {0, 0, 2, 0, 6, 0, 0, 0, 4},
        {8, 0, 0, 0, 0, 0, 0, 0, 5},
        {4, 0, 0, 0, 1, 0, 8, 0, 0},
        {0, 0, 0, 0, 8, 7, 1, 0, 0},
        {0, 8, 0, 5, 0, 0, 0, 0, 7},
        {0, 0, 7, 0, 0, 2, 0, 9, 0}
    };
    psolve_packet *sexp = psolve(s4);
    gettimeofday(&t1, '\0');
    end = (backtrack(sexp) - t1.tv_usec);
    print_board(sexp);
    free(sexp);
    printf("\n%.3lf milliseconds\n", end * pow(10, -3));
    mil_counter += end;

    printf("\n%.3lf milliseconds total\n", mil_counter * pow(10, -3));

    return 0;
}
