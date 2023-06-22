=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: kathyjia
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
     I used a 2d array of type Cell to represent the game board, with each element
     representing a square in the grid. I think this is an appropriate use of a 2d
     array because it is convenient to iterate through the board and access each
     individual Cell using nested for loops. In addition, the size of a 2d array is
     fixed, and the size of the board did not need to be changed.

  2. Collections and/or Maps
     I used an ArrayList of type Cell to contain all the squares of the game board
     that were flagged by the player. I think this is an appropriate use of a
     collection because ArrayLists are resizable, and the number of flagged squares
     changes as the player progresses through the game. This makes it easy to add
     and remove cells as the player flags and un-flags squares during the game. An
     ArrayList also makes it convenient to check whether a given square is flagged
     (by checking if it is contained in the ArrayList).

  3. JUnit Testable Component
     I wrote JUnit tests to make sure my code was functioning as intended and to
     check for any underlying bugs. I tested the EmptyCell, NumberCell, and
     MineCell classes, as well as the Minesweeper class and the GameBoard class. I
     think this is an appropriate use of testing because it ensures that my program
     correctly handles unusual input (edge cases). Also, it can be hard to test
     exhaustively using the GUI, as it is more time-consuming and the GUI is only
     one component of the program.

  4. Recursion
     I used recursion to reveal all the adjacent non-mine squares of an empty square,
     as well as all adjacent non-mine squares of every new empty square revealed. I
     chose to use recursion, as opposed to iteration, because the number of squares
     that need to be revealed can differ depending on the number of mines in the
     area, and recursion makes it easy to continuously check each surrounding square
     and repeat the process for each new empty square revealed, spreading outwards
     until there are no more new empty cells.


===============================
=: File Structure Screenshot :=
===============================

- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Cell - an interface implemented by EmptyCell, NumberCell, and MineCell. I
         created this so I could put the three different types of cells into
         the same 2d array.
  EmptyCell - a class that implements Cell; represents a cell that has no
              adjacent mines; drawn as a black square
  NumberCell - a class that implements Cell; represents a cell that has a
               certain number of adjacent mines; drawn as a number (val)
  MineCell - a class that implements Cell; represents a cell that contains a
             mine; drawn as a circle
  Minesweeper - a model for the game; contains most of the game's logic,
                including code for resetting the game board, initializing a
                new board based on the location of a first click, revealing
                cells after each click/turn, and checking whether the player
                has won
  GameBoard - a class that contains most of the controller and view
              functionality, including code for handling mouse clicks,
              resetting the game, updating the status and count JLabels, and
              repainting the board.
  RunMinesweeper - a class that initializes the view and implements a bit of
                   controller functionality; contains code that displays the
                   frame and widgets (status panel, count panel, game board,
                   reset button), as well as the instructions
  GameTest - contains tests for EmptyCell, NumberCell, MineCell, Minesweeper,
             and GameBoard classes

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  I had a hard time figuring out how to prevent the player from continuing to
  reveal cells after the game had ended, but other than that I did not have any
  major issues, as I made use of the TicTacToe example code.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think there is a good separation of functionality, as RunMinesweeper initializes
  the view and involves a bit of the controller through the reset button, GameBoard
  handles most of the controller and view functionality, and Minesweeper serves as
  the model of the game. I also think private state is well-encapsulated, since I
  created a number of getter and setter functions for private variables. If given the
  chance, I would break the paintComponent in GameBoard into different methods and
  possibly classes in order to make the code easier to read and reuse.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
https://docs.oracle.com/javase/6/docs/api/javax/swing/SwingUtilities.html#isLeftMouseButton(java.awt.event.MouseEvent)
https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.htm