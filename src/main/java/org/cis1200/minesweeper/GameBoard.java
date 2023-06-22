package org.cis1200.minesweeper;

import java.util.ArrayList;

/**
 * CIS 120 HW09 - Minesweeper (based on TicTacToe Demo created by
 * Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.SwingUtilities.isRightMouseButton;
import static javax.swing.SwingUtilities.isLeftMouseButton;

/**
 * This class instantiates a Minesweeper object, which is the model for the
 * game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 *
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Minesweeper ms; // model for the game
    private JLabel status; // current status text
    private JLabel count; // current mine count text

    // game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit, JLabel countInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        ms = new Minesweeper(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        count = countInit; // initializes the mine count JLabel

        /*
         * Listens for mouse clicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                // updates the model given the coordinates of the mouseclick
                if (ms.getFirstTurn()) {
                    ms.firstTurn(p.x / 25, p.y / 25);
                } else if (!ms.checkWin() && !ms.getLoss()) {
                    Cell c = ms.getCell(p.x / 25, p.y / 25);
                    if (isRightMouseButton(e) && ms.getFlagged().contains(c)) {
                        ms.removeFlag(c);
                    } else if (isRightMouseButton(e) && !c.getRevealed() &&
                            ms.getNumMinesRemaining() > 0) {
                        ms.addFlag(c);
                    } else if (isLeftMouseButton(e)) {
                        ms.playTurn(c);
                    }
                }
                updateStatus(); // updates the status JLabel
                updateCount(); // updates the mine count JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ms.reset();
        status.setText("playing");
        count.setText("40");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (ms.checkWin()) {
            status.setText("You won!!!");
        } else if (ms.getLoss()) {
            status.setText("You lost :(");
        }
    }

    /**
     * Updates the JLabel to reflect the current mine count.
     */
    private void updateCount() {
        int mineCount = ms.getNumMinesRemaining();
        count.setText("" + mineCount);
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw grid lines of the board
        for (int i = 25; i <= 400; i += 25) {
            g.drawLine(i, 0, i, 400);
            g.drawLine(0, i, 400, i);
        }

        // draw flags
        ArrayList<Cell> flagged = ms.getFlagged();
        for (Cell flag : flagged) {
            g.fillPolygon(
                    new int[] { 8 + flag.getX() * 25, 17 + flag.getX() * 25, 8 +
                            flag.getX() * 25 },
                    new int[] { 8 + flag.getY() * 25, 12 + flag.getY() * 25, 17 +
                            flag.getY() * 25 },
                    3
            );
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell c = ms.getCell(i, j);
                if (c != null) {
                    if (ms.getLoss()) {
                        // draw all mine cells + x's over wrongly flagged cells if player lost
                        if ((c.getRevealed() || c instanceof MineCell) && !flagged.contains(c)) {
                            c.draw(g, i, j);
                        } else if (flagged.contains(c) && !(c instanceof MineCell)) {
                            g.drawLine(5 + i * 25, 5 + j * 25, 20 + i * 25, 20 + j * 25);
                            g.drawLine(5 + i * 25, 20 + j * 25, 20 + i * 25, 5 + j * 25);
                        }
                    } else if (c.getRevealed() && !flagged.contains(c)) {
                        // draw empty + number cells if they are revealed and not flagged
                        c.draw(g, i, j);
                    }
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    /**
     * Returns the model for minesweeper (for testing purposes).
     */
    public Minesweeper getMs() {
        return ms;
    }

    /**
     * Returns the status JLabel (for testing purposes).
     */
    public JLabel getStatus() {
        return status;
    }

    /**
     * Returns the mine count JLabel (for testing purposes).
     */
    public JLabel getCount() {
        return count;
    }
}