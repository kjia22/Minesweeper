package org.cis1200.minesweeper;

import java.awt.*;

public class EmptyCell implements Cell {
    private int x, y;
    private boolean isRevealed;

    public EmptyCell(int x, int y) {
        this.x = x;
        this.y = y;
        isRevealed = false;
    }

    public void draw(Graphics g, int x, int y) {
        g.fillRect(1 + x * 25, 1 + y * 25, 23, 23);
    }

    public void setRevealed(boolean b) {
        isRevealed = b;
    }

    public boolean getRevealed() {
        return isRevealed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}