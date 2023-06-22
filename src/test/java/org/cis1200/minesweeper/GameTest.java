package org.cis1200.minesweeper;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {
    // EmptyCell.java tests
    @Test
    public void testEmptyCellConstructor() {
        Cell c = new EmptyCell(5, 7);
        assertEquals(5, c.getX());
        assertEquals(7, c.getY());
        assertFalse(c.getRevealed());
    }

    @Test
    public void testEmptyCellSetRevealed() {
        Cell c = new EmptyCell(1, 2);
        c.setRevealed(true);
        assertTrue(c.getRevealed());
    }

    // NumberCell.java tests
    @Test
    public void testNumberCellConstructor() {
        Cell c = new NumberCell(5, 1, 2);
        assertEquals(1, c.getX());
        assertEquals(2, c.getY());
        assertFalse(c.getRevealed());
    }

    @Test
    public void testNumberCellSetRevealed() {
        Cell c = new NumberCell(5, 1, 2);
        c.setRevealed(true);
        assertTrue(c.getRevealed());
    }

    // MineCell.java tests
    @Test
    public void testMineCellConstructor() {
        Cell c = new MineCell(5, 6);
        assertEquals(5, c.getX());
        assertEquals(6, c.getY());
        assertFalse(c.getRevealed());
    }

    @Test
    public void testMineCellSetRevealed() {
        Cell c = new MineCell(2, 3);
        c.setRevealed(true);
        assertTrue(c.getRevealed());
    }

    // Minesweeper.java tests
    @Test
    public void testMinesweeperConstructor() {
        Minesweeper ms = new Minesweeper();
        assertNull(ms.getCell(0, 0));
        assertEquals(new ArrayList<Cell>(), ms.getFlagged());
        assertEquals(40, ms.getNumMinesRemaining());
        assertFalse(ms.getLoss());
        assertTrue(ms.getFirstTurn());
    }

    @Test
    public void testMinesweeperFirstTurn() {
        Minesweeper ms = new Minesweeper();
        ms.firstTurn(1, 2);
        assertTrue(ms.getCell(1, 2).getRevealed());
        assertFalse(ms.getFirstTurn());
    }

    @Test
    public void testMinesweeperCreateMineCellsFirstClickEmpty() {
        Minesweeper ms = new Minesweeper();
        ms.createMineCells(1, 2);

        for (int i = 0; i <= 2; i++) {
            for (int j = 1; j <= 3; j++) {
                assertFalse(ms.getCell(i, j) instanceof MineCell);
            }
        }
    }

    @Test
    public void testMinesweeperFillBoardFirstClickEmpty() {
        Minesweeper ms = new Minesweeper();
        ms.createMineCells(2, 1);
        ms.fillBoard();

        assertTrue(ms.getCell(2, 1) instanceof EmptyCell);
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j <= 2; j++) {
                Cell c = ms.getCell(i, j);
                assertTrue(c instanceof EmptyCell || c instanceof NumberCell);
            }
        }
    }

    @Test
    public void testMinesweeperFillBoard() {
        Minesweeper ms = new Minesweeper();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                ms.setMine(i, j);
            }
        }
        ms.fillBoard();

        for (int i = 0; i < 8; i++) {
            assertTrue(ms.getCell(i, 5) instanceof NumberCell);
        }
        for (int j = 0; j < 5; j++) {
            assertTrue(ms.getCell(8, j) instanceof NumberCell);
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 6; j < 16; j++) {
                assertTrue(ms.getCell(i, j) instanceof EmptyCell);
            }
        }
        for (int i = 9; i < 16; i++) {
            for (int j = 0; j < 5; j++) {
                assertTrue(ms.getCell(i, j) instanceof EmptyCell);
            }
        }
    }

    @Test
    public void testMinesweeperCountAdjacentMines() {
        Minesweeper ms = new Minesweeper();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                ms.setMine(i, j);
            }
        }
        ms.fillBoard();

        assertEquals(2, ms.countAdjacentMines(0, 5));
        assertEquals(2, ms.countAdjacentMines(7, 5));
        assertEquals(1, ms.countAdjacentMines(8, 5));
        assertEquals(2, ms.countAdjacentMines(8, 4));
        assertEquals(2, ms.countAdjacentMines(8, 0));
        for (int i = 1; i < 7; i++) {
            assertEquals(3, ms.countAdjacentMines(i, 5));
        }
        for (int j = 1; j < 4; j++) {
            assertEquals(3, ms.countAdjacentMines(8, j));
        }
    }

    @Test
    public void testMinesweeperPlayTurnClickMine() {
        Minesweeper ms = new Minesweeper();
        ms.setMine(1, 1);
        ms.fillBoard();
        ms.playTurn(ms.getCell(1, 1));
        assertTrue(ms.getLoss());
    }

    @Test
    public void testMinesweeperPlayTurnClickEmptyCell() {
        Minesweeper ms = new Minesweeper();
        ms.fillBoard();
        Cell c = ms.getCell(1, 1);
        ms.playTurn(c);
        assertTrue(c.getRevealed());
        assertTrue(c instanceof EmptyCell);
    }

    @Test
    public void testMinesweeperPlayTurnClickNumberCell() {
        Minesweeper ms = new Minesweeper();
        ms.setMine(1, 1);
        ms.fillBoard();
        Cell c = ms.getCell(1, 2);
        ms.playTurn(c);
        assertTrue(c.getRevealed());
        assertTrue(c instanceof NumberCell);
    }

    @Test
    public void testMinesweeperRevealAdjacentCellsNoMines() {
        Minesweeper ms = new Minesweeper();
        ms.fillBoard();
        ms.playTurn(ms.getCell(1, 1));

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell c = ms.getCell(i, j);
                assertTrue(c.getRevealed());
                assertTrue(c instanceof EmptyCell);
            }
        }
    }

    @Test
    public void testMinesweeperRevealAdjacentCellsNumberCell() {
        Minesweeper ms = new Minesweeper();
        ms.setMine(0, 0);
        ms.fillBoard();
        Cell c = ms.getCell(1, 1);
        ms.playTurn(c);

        assertTrue(c.getRevealed());
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i != 1 || j != 1) {
                    assertFalse(ms.getCell(i, j).getRevealed());
                }
            }
        }
    }

    @Test
    public void testMinesweeperCheckWinTrue() {
        Minesweeper ms = new Minesweeper();
        ms.fillBoard();
        ms.playTurn(ms.getCell(1, 1));
        assertTrue(ms.checkWin());
    }

    @Test
    public void testMinesweeperCheckWinFalse() {
        Minesweeper ms = new Minesweeper();
        ms.setMine(1, 1);
        ms.fillBoard();
        ms.playTurn(ms.getCell(1, 1));
        assertFalse(ms.checkWin());
    }

    @Test
    public void testMinesweeperGetLossFalse() {
        Minesweeper ms = new Minesweeper();
        ms.fillBoard();
        ms.playTurn(ms.getCell(1, 1));
        assertFalse(ms.getLoss());
    }

    @Test
    public void testMinesweeperGetLossTrue() {
        Minesweeper ms = new Minesweeper();
        ms.setMine(1, 1);
        ms.fillBoard();
        ms.playTurn(ms.getCell(1, 1));
        assertTrue(ms.getLoss());
    }

    @Test
    public void testMinesweeperGetCell() {
        Minesweeper ms = new Minesweeper();
        ms.setMine(1, 1);
        ms.fillBoard();
        assertTrue(ms.getCell(1, 1) instanceof MineCell);
        assertTrue(ms.getCell(5, 5) instanceof EmptyCell);
        assertTrue(ms.getCell(2, 2) instanceof NumberCell);
        assertTrue(ms.getCell(1, 2) instanceof NumberCell);
    }

    @Test
    public void testMinesweeperAddFlag() {
        Minesweeper ms = new Minesweeper();
        ms.firstTurn(2, 2);
        Cell c = ms.getCell(3, 3);
        ms.addFlag(c);
        assertEquals(new ArrayList<Cell>(Collections.singleton(c)), ms.getFlagged());
    }

    @Test
    public void testMinesweeperAddMultipleFlags() {
        Minesweeper ms = new Minesweeper();
        ms.firstTurn(2, 2);
        ms.addFlag(ms.getCell(3, 3));
        ms.addFlag(ms.getCell(3, 4));

        ArrayList<Cell> flagged = new ArrayList<Cell>();
        flagged.add(ms.getCell(3, 3));
        flagged.add(ms.getCell(3, 4));

        assertEquals(flagged, ms.getFlagged());
    }

    @Test
    public void testMinesweeperRemoveFlag() {
        Minesweeper ms = new Minesweeper();
        ms.firstTurn(2, 2);
        Cell c = ms.getCell(2, 2);
        Cell c2 = ms.getCell(3, 3);
        ms.addFlag(c);
        ms.addFlag(c2);
        ms.removeFlag(c2);
        assertEquals(new ArrayList<Cell>(Collections.singleton(c)), ms.getFlagged());
    }

    @Test
    public void testMinesweeperGetNumMinesRemaining() {
        Minesweeper ms = new Minesweeper();
        ms.firstTurn(2, 2);
        assertEquals(40, ms.getNumMinesRemaining());
        ms.addFlag(ms.getCell(1, 1));
        ms.addFlag(ms.getCell(2, 2));
        ms.removeFlag(ms.getCell(2, 2));
        ms.addFlag(ms.getCell(5, 5));
        assertEquals(38, ms.getNumMinesRemaining());
    }

    // GameBoard.java tests
    @Test
    public void testGameBoardConstructor() {
        JLabel status = new JLabel("Setting up...");
        JLabel count = new JLabel("40");
        GameBoard gb = new GameBoard(status, count);

        Minesweeper ms = gb.getMs();
        assertEquals(40, ms.getNumMinesRemaining());
        assertFalse(ms.getLoss());
        assertTrue(ms.getFirstTurn());

        assertEquals(status, gb.getStatus());
        assertEquals(count, gb.getCount());
    }

    @Test
    public void testGameBoardReset() {
        JLabel status = new JLabel("Setting up...");
        JLabel count = new JLabel("40");
        GameBoard gb = new GameBoard(status, count);

        Minesweeper ms = gb.getMs();
        JLabel gbStatus = gb.getStatus();
        JLabel gbCount = gb.getCount();

        ms.firstTurn(1, 2);
        assertFalse(ms.getFirstTurn());

        gb.reset();
        assertTrue(ms.getFirstTurn());
        assertEquals("playing", gbStatus.getText());
        assertEquals("40", gbCount.getText());
    }

    @Test
    public void testGameBoardGetPreferredSize() {
        JLabel status = new JLabel("Setting up...");
        JLabel count = new JLabel("40");
        GameBoard gb = new GameBoard(status, count);

        assertEquals(new Dimension(400, 400), gb.getPreferredSize());
    }
}