# three-language-compare-sudoku-solver

This project will directly help me understand speed differences between the 
programing languages Java, C and Python. Each language is used to implement a 
sudoku solving algorithm based on the same psudocode. indicated in the file 
psudocode.txt

All aspects of development are happening on the terminal. Since I learned how to
use many unix commands and was forced to use vim in my recent Mechanics of 
Programing class, learned to properly use github's integration with git in
my recent Introduction to Software Engineering class, I will merge those two 
ideas (since it is faster anyway) to produce this project.

Some things to keep in mind:
	I am using python's object orientation to make it comparable to the java
		code. It may be faster if I did not, so that may change if the python
		time is close to the C time

## My Predictions
### Python - C
Since Python is kindof built on C, it seems like C will be faster than Python.
Even though I think this, they may not be too much different since we are
populating and initializing the arrays at the same time, there will not be too
much space wasted from python having to guess what size to actually store the
arrays. Writing the Python code will be faster though.
