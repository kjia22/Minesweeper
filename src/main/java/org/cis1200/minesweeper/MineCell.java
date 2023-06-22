package org.cis1200.minesweeper;

import java.awt.*;

public class MineCell implements Cell {
    private int x, y;
    private boolean isRevealed;

    public MineCell(int x, int y) {
        this.x = x;
        this.y = y;
        isRevealed = false;
    }

    public void draw(Graphics g, int x, int y) {
        g.fillOval(7 + x * 25, 7 + y * 25, 11, 11);
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