import time

class Sudoku:
    
    def __init__(self, s1):
        self.row = -1
        self.col = 8
        self.board = s1


    def backtrack(self):
        print(self.board)
        print(self.row)
        print(self.col)


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
sea.backtrack()
end = (time.time() - start) * 10 * 10 * 10
print(end)
