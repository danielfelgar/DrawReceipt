package com.github.danielfelgar.drawreceiptlib;

import android.graphics.Canvas;

/**
 * Created by daniel on 12/08/2016.
 */
public interface IDrawItem {
    void drawOnCanvas(Canvas canvas, float x, float y);

    int getHeight();
}
