package org.cis1200.minesweeper;

import java.awt.*;

public interface Cell {
    void draw(Graphics g, int x, int y);

    void setRevealed(boolean b);

    boolean getRevealed();

    int getX();

    int getY();
}