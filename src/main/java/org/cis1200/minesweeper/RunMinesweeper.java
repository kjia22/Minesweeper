package org.cis1200.minesweeper;

/**
 * CIS 120 HW09 - Minesweeper (based on TicTacToe Demo created by
 * Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020)
 */

import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 */
public class RunMinesweeper implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // frame that contains instructions for game
        final JFrame instructionsFrame = new JFrame("Instructions");
        instructionsFrame.setLocation(200, 100);

        // instructions text
        final JPanel instructions_panel = new JPanel();
        instructionsFrame.add(instructions_panel, BorderLayout.CENTER);
        final JTextArea text = new JTextArea("""
                In Minesweeper, you start with a covered 16x16 board of squares
                with 40 randomly placed mines. The objective of this game is to
                uncover all squares that do not contain mines without uncovering
                a square that contains a mine. To uncover a square, click on it
                with your mouse. If you accidentally uncover a mine, the game
                ends and you lose. If you uncover all squares except for the 40
                mines, the game ends and you win! Each square that you uncover
                will either be empty (black), contain a number, or contain a circle
                (bomb--in which case you lose). If a square contains a number,
                that number indicates the number of mines that are adjacent to
                the square. If a square is empty, that means there are no mines in
                the 8 adjacent squares. A useful way to keep track of square that
                you think contain mines is to flag them by right clicking on the
                square. This will mark the square with a triangle and prevent you
                from accidentally clicking on it. To un-flag a square, simply right
                click on it again. The number to the left of the board indicates the
                number of mines left to find. Flagging a square decrements this
                number by 1, and un-flagging a square increments it by 1. To
                play again, click the 'reset' button.""");
        instructions_panel.add(text);

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(500, 200);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Mine count panel
        final JPanel mineCount_panel = new JPanel();
        frame.add(mineCount_panel, BorderLayout.WEST);
        final JLabel count = new JLabel("40");
        mineCount_panel.add(count);

        // Game board
        final GameBoard board = new GameBoard(status, count);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        // Put the frames on the screen
        frame.pack();
        instructionsFrame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        instructionsFrame.setVisible(true);

        // Start the game
        board.reset();
    }
}