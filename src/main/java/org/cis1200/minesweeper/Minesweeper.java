package org.cis1200.minesweeper;

import java.util.ArrayList;

/**
 * CIS 120 HW09 - Minesweeper (based on TicTacToe Demo created by
 * Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020)
 */

/**
 * This class is a model for Minesweeper.
 */
public class Minesweeper {

    private Cell[][] board; // represents the game board
    private ArrayList<Cell> flagged; // contains all flagged cells
    private boolean loss; // indicates whether player has lost
    private boolean firstTurn; // indicates whether it is currently the first turn of the game
    private int numMinesRemaining; // number of mines left to find/flag

    /**
     * Constructor sets up game state.
     */
    public Minesweeper() {
        reset();
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new Cell[16][16];
        flagged = new ArrayList<Cell>();
        numMinesRemaining = 40;
        loss = false;
        firstTurn = true;
    }

    /**
     * firstTurn initializes the game board (randomly generates mine cells and
     * populates the rest of the board with number cells and empty cells) and
     * reveals all necessary cells (including clicked cell).
     *
     * @param x column of first clicked cell
     * @param y row of first clicked cell
     */
    public void firstTurn(int x, int y) {
        createMineCells(x, y);
        fillBoard();
        board[x][y].setRevealed(true);
        revealAdjacentCells(x, y);
        firstTurn = false;
    }

    /**
     * createMineCells randomly generates 40 mine cells and places them in the
     * board. It ensures that no mines are placed in or around the clicked cell
     * (specified by the method arguments), so the first click always reveals
     * an empty cell.
     *
     * @param x column of first clicked cell
     * @param y row of first clicked cell
     */
    public void createMineCells(int x, int y) {
        int numMines = 40;
        int numCreatedMines = 0;
        while (numCreatedMines < numMines) {
            int xPos = (int) Math.floor(Math.random() * 16);
            int yPos = (int) Math.floor(Math.random() * 16);
            // make sure no mines are placed on/around first cell clicked or in
            // the same spot as another mine
            if (((xPos < x - 1 || xPos > x + 1) || (yPos < y - 1 || yPos > y + 1))
                    && board[xPos][yPos] == null) {
                board[xPos][yPos] = new MineCell(xPos, yPos);
                numCreatedMines++;
            }
        }
    }

    /**
     * fillBoard assumes the board has already been filled with mines and
     * populates the rest of the board with empty/number cells accordingly
     */
    public void fillBoard() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i][j] == null) {
                    int adjMines = countAdjacentMines(i, j);
                    if (adjMines > 0) {
                        board[i][j] = new NumberCell(adjMines, i, j);
                    } else {
                        board[i][j] = new EmptyCell(i, j);
                    }
                }
            }
        }
    }

    /**
     * countAdjacentMines counts the number of mines in the 8 cells adjacent to
     * the given cell (specified by the method arguments).
     *
     * @param x column of the given cell
     * @param y row of the given cell
     * @return an integer denoting the number of mines adjacent to the given cell
     */
    public int countAdjacentMines(int x, int y) {
        int adjMines = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < 16 && j >= 0 && j < 16 && (i != x || j != y)) {
                    if (board[i][j] instanceof MineCell) {
                        adjMines++;
                    }
                }
            }
        }
        return adjMines;
    }

    /**
     * playTurn allows player to play a turn.
     *
     * @param c cell to play in
     */
    public void playTurn(Cell c) {
        if (!flagged.contains(c) && !(c instanceof MineCell)) {
            c.setRevealed(true);
            if (c instanceof EmptyCell) {
                revealAdjacentCells(c.getX(), c.getY());
            }
        } else if (!flagged.contains(c) && c instanceof MineCell) {
            loss = true;
        }
    }

    /**
     * revealAdjacentCells assumes the given cell (specified by method args)
     * is empty and reveals all adjacent non-mine cells, repeating this for
     * every other empty cell revealed
     *
     * @param x column of the given cell
     * @param y row of the given cell
     */
    public void revealAdjacentCells(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < 16 && j >= 0 && j < 16 && (i != x || j != y)) {
                    Cell c = board[i][j];
                    // base case
                    if (c instanceof NumberCell && !c.getRevealed()) {
                        c.setRevealed(true);
                    } else if (c instanceof EmptyCell && !c.getRevealed()) {
                        c.setRevealed(true);
                        revealAdjacentCells(i, j); // recursive call
                    }
                }
            }
        }
    }

    /**
     * checkWin checks whether the game has reached a win condition.
     *
     * @return true if player has won and false if player has not won
     */
    public boolean checkWin() {
        if (loss) {
            return false;
        }
        boolean win = true;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell c = board[i][j];
                if (!(c instanceof MineCell)) {
                    win = win && c.getRevealed();
                }
            }
        }
        return win;
    }

    /**
     * getLoss is a getter for the loss variable.
     *
     * @return a boolean indicating whether the player has lost; true
     *         if player has lost and false if player has not lost
     */
    public boolean getLoss() {
        return loss;
    }

    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param x column to retrieve
     * @param y row to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    /**
     * setCell sets a given cell (specified by method args) to a MineCell
     * (for testing purposes).
     *
     * @param x column of given cell
     * @param y row of given cell
     */
    public void setMine(int x, int y) {
        board[x][y] = new MineCell(x, y);
    }

    /**
     * getNumMinesRemaining is a getter for the numMinesRemaining variable.
     *
     * @return an integer denoting the number of mines left to find (based on
     *         the number of cells flagged by the player)
     */
    public int getNumMinesRemaining() {
        return numMinesRemaining;
    }

    /**
     * getFirstTurn is a getter for the firstTurn variable.
     *
     * @return a boolean indicating whether it is currently the first turn/click
     *         of the game
     */
    public boolean getFirstTurn() {
        return firstTurn;
    }

    /**
     * getFlagged is a getter for the flagged variable.
     *
     * @return a Cell ArrayList that contains all cells flagged by the player
     */
    public ArrayList<Cell> getFlagged() {
        return flagged;
    }

    /**
     * addFlag flags a given cell by adding it to the flagged ArrayList and
     * increments numMinesRemaining
     *
     * @param c cell to flag
     */
    public void addFlag(Cell c) {
        flagged.add(c);
        numMinesRemaining--;
    }

    /**
     * removeFlag un-flags a given cell by removing it from the flagged ArrayList
     * and decrements numMinesRemaining
     *
     * @param c cell to un-flag
     */
    public void removeFlag(Cell c) {
        flagged.remove(c);
        numMinesRemaining++;
    }
}