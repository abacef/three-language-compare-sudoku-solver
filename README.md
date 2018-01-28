# three-language-compare-sudoku-solver

This project will directly help me understand speed differences between the 
programing languages Java, C and Python. Each language is used to implement a 
sudoku solving algorithm based on the same psudocode, indicated in the file 
psudocode.txt

Since what we are timing is a backtracking algorithm, the actions of a
programing language that we are comparing is function calls, recursion, and a 
little math. Aspects of language function like array memory alocation speeds, 
maximum memory usage, speed of class/struct creation and manipulation, IO and 
threads are not tested.

All aspects of development are happening on the terminal. Since I learned how to
use many unix commands and was forced to use vim in my recent Mechanics of 
Programing class, learned to properly use github's integration with git in
my recent Introduction to Software Engineering class, I will merge those two 
ideas (since it is faster anyway) to develop this project.

Some things to keep in mind:
	I am using python object orientation for this project. By doing this, time
    will not take too much of a hit. No real creation, destroying, manipulating
    or outside accessing is being done, and there is only one class, so it seems
    fine to me.

### Timing Uncertianty
Since there is not a unified implementation of timing some code between these
languages, the percision may be low on each algorithm since there is no one
centralized way or timing algorithm used. What we are banking on though is the
high accuracy of each respective timing methods. If one language uses system
time, and another one uses clock time for a specific implementation, at least
each time elapsed will be accurate.

## My Predictions
### Python - C
Since Python is kindof built on C, therefore with translation and assumtion, it 
seems like C will be faster than Python.
Even though I think this, they may not be too different since we are
populating and initializing the arrays at the same time, there will not be too
much space wasted from python having to guess what size to actually store the
arrays. Writing the Python code will be a small bit faster because of the no
semi colons or curly braces.

### C - Java
Well, these are both compiled languages, so it seems like there will be a
significant difference between these two and Python. The compilation process of
these two languages is different though. C goes from source code to assembly,
and then to machine code (1s and 0s). In Java though, the first substantiave
process is compiling source code to bytecode. The machine code cannot read this
byte code though. The installed Java just in time compiler written for the
specific archetectures actually translates this byte code to machine code at the
instant it is run. Given these compilation differences, I would say the Java
code will run a significant amount slower than C code, but the difference will
not even come close to Python's inneficiency between these two.
