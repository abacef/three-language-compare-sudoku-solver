import time
import sys

class Sudoku:
    
    def __init__(self, s1):
        self.row = -1
        self.col = 8
        self.board = s1
        self.board_cpy = []
        for i in range(0, 9):
            self.board_cpy.append([])
            for j in range(0, 9):
                self.board_cpy[i].append(self.board[i][j])


    def print_board(self):
        for i in range(0, 9):
            print()
            for j in range(0, 9):
                print(self.board[i][j], end = " ")


    def check_square(self):
        r = -1
        c = -1
        if self.row < 3:
            r = 0
        elif self.row < 6:
            r = 3
        else:
            r = 6
        
        if self.col < 3:
            c = 0
        elif self.col < 6:
            c = 3
        else:
            c = 6
        
        num = self.board[self.row][self.col]
        for i in range(0, 3):
            for j in range(0, 3):
                if ((i + r) is not self.row or (j + c) is not self.col) and \
                                                     self.board[i + r][j + c] is num:
                    return False
        return True


    def is_valid(self):
        num = self.board[self.row][self.col]
        for i in range(0, 9):
            if i is not self.col and self.board[self.row][i] is num:
                return False
            if i is not self.row and self.board[i][self.col] is num:
                return False
        return self.check_square()


    def deadvance(self):
        if self.col is 0:
            if self.row is not 0:
                self.col = 8
                self.row = self.row - 1
        else:
            self.col = self.col - 1
        if not(self.col is 0 and self.row is 0):
            if self.board_cpy[self.row][self.col] is not 0:
                self.deadvance()


    def advance(self):
        if self.col is 8:
            self.col = 0
            self.row = self.row + 1
            print("right now -----> " + str(self.row) + ", " + str(self.col))
        else:
            self.col = self.col + 1
        if not(self.row is 9 and self.col is 0):
            print("right now -------> " + str(self.row) + ", " + str(self.col))
            if self.board_cpy[self.row][self.col] is not 0:
                self.advance()


    def backtrack(self):
        self.advance()
        if self.row == 9 and self.col == 0:
            return time.time()
        else:
            for i in range(1, 10):
                self.board[self.row][self.col] = i
                print("testing " + str(i) + " at " + str(self.row) + ", " +
                                                str(self.col))
                if self.is_valid():
                    num = self.backtrack()
                    if num != 0:
                        return num
            self.board[self.row][self.col] = 0
            self.deadvance()
        return 0

mil_counter = 0
s10 = [0, 0, 7, 0, 5, 0, 0, 9, 3]
s11 = [5, 8, 0, 7, 0, 0, 1, 6, 0]
s12 = [9, 0, 6, 8, 0, 3, 0, 2, 0]
s13 = [1, 0, 0, 0, 0, 0, 6, 0, 0]
s14 = [8, 6, 0, 0, 0, 0, 0, 7, 2]
s15 = [0, 0, 2, 0, 0, 0, 0, 0, 1]
s16 = [0, 5, 0, 3, 0, 9, 7, 0, 8]
s17 = [0, 2, 8, 0, 0, 4, 0, 1, 9]
s18 = [3, 9, 0, 0, 7, 0, 2, 0, 0]
s1 = [s10, s11, s12, s13, s14, s15, s16, s17, s18]
sea = Sudoku(s1)
start = time.time()
end = (sea.backtrack() - start) * 10 * 10 * 10
print()
print(end)
mil_counter = mil_counter + end
"""
s20 = [0, 0, 4, 0, 3, 0, 8, 0, 0]
s21 = [0, 0, 0, 0, 0, 4, 5, 0, 0]
s22 = [2, 6, 0, 8, 0, 0, 3, 0, 0]
s23 = [4, 0, 0, 0, 7, 9, 0, 0, 6]
s24 = [3, 0, 0, 1, 4, 8, 0, 0, 7]
s25 = [7, 0, 0, 3, 6, 0, 0, 0, 8]
s26 = [0, 0, 9, 0, 0, 3, 0, 8, 5]
s27 = [0, 0, 1, 6, 0, 0, 0, 0, 0]
s28 = [0, 0, 3, 0, 2, 0, 6, 0, 0]
s2 = [s20, s21, s22, s23, s24, s25, s26, s27, s28]
smed = Sudoku(s2)
start = time.time()
end = (sea.backtrack() - start) * 10 * 10 * 10
print()
print(end)
mil_counter = mil_counter + end
"""
