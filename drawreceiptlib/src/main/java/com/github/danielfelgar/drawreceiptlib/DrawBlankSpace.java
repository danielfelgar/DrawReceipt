package com.github.danielfelgar.drawreceiptlib;

import android.graphics.Canvas;

/**
 * Created by daniel on 15/08/2016.
 */
public class DrawBlankSpace implements IDrawItem {

    private int blankSpace;

    public DrawBlankSpace(int blankSpace) {
        this.blankSpace = blankSpace;
    }

    @Override
    public void drawOnCanvas(Canvas canvas, float x, float y) {
    }

    @Override
    public int getHeight() {
        return blankSpace;
    }
}
