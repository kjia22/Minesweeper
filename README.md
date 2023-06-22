# Minesweeper
CIS 1200 Game Project

**Core Concepts**
  1. 2D Arrays
     I used a 2d array of type Cell to represent the game board, with each element representing a square in the grid. I think this is an
     appropriate use of a 2d array because it is convenient to iterate through the board and access each individual Cell using nested for
     loops. In addition, the size of a 2d array is fixed, and the size of the board did not need to be changed.

  3. Collections and/or Maps
     I used an ArrayList of type Cell to contain all the squares of the game board that were flagged by the player. I think this is an
     appropriate use of a collection because ArrayLists are resizable, and the number of flagged squares changes as the player progresses
     through the game. This makes it easy to add and remove cells as the player flags and un-flags squares during the game. An ArrayList
     also makes it convenient to check whether a given square is flagged (by checking if it is contained in the ArrayList).

  5. JUnit Testable Component
     I wrote JUnit tests to make sure my code was functioning as intended and to check for any underlying bugs. I tested the EmptyCell,
     NumberCell, and MineCell classes, as well as the Minesweeper class and the GameBoard class. I think this is an appropriate use of
     testing because it ensures that my program correctly handles unusual input (edge cases). Also, it can be hard to test exhaustively
     using the GUI, as it is more time-consuming and the GUI is only one component of the program.

  7. Recursion
     I used recursion to reveal all the adjacent non-mine squares of an empty square, as well as all adjacent non-mine squares of every new
     empty square revealed. I chose to use recursion, as opposed to iteration, because the number of squares that need to be revealed can
     differ depending on the number of mines in the area, and recursion makes it easy to continuously check each surrounding square and
     repeat the process for each new empty square revealed, spreading outwards until there are no more new empty cells.

**Classes:**\
Cell\
An interface implemented by EmptyCell, NumberCell, and MineCell. I created this so I could put the three different types of cells into the 
same 2d array.

EmptyCell\
A class that implements Cell; represents a cell that has no adjacent mines; drawn as a black square

NumberCell\
A class that implements Cell; represents a cell that has a certain number of adjacent mines; drawn as a number (val)

MineCell\
Aclass that implements Cell; represents a cell that contains a mine; drawn as a circle

Minesweeper\
A model for the game; contains most of the game's logic, including code for resetting the game board, initializing a new board based on the 
location of a first click, revealing cells after each click/turn, and checking whether the player has won

GameBoard\
A class that contains most of the controller and view functionality, including code for handling mouse clicks, resetting the game, updating the status and count JLabels, and repainting the board.

RunMinesweeper\
A class that initializes the view and implements a bit of controller functionality; contains code that displays the frame and widgets (status panel, count panel, game board, reset button), as well as the instructions

GameTest\
Contains tests for EmptyCell, NumberCell, MineCell, Minesweeper, and GameBoard classes
