package org.cis1200.minesweeper;

import java.awt.*;

public class NumberCell implements Cell {
    private int x, y;
    private boolean isRevealed;
    private int val;

    public NumberCell(int val, int x, int y) {
        this.val = val;
        this.x = x;
        this.y = y;
        isRevealed = false;
    }

    public void draw(Graphics g, int x, int y) {
        g.drawString("" + val, 9 + x * 25, 17 + y * 25);
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
