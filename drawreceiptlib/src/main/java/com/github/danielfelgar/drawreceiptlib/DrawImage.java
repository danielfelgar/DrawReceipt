package com.github.danielfelgar.drawreceiptlib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by daniel on 15/08/2016.
 */
public class DrawImage implements IDrawItem {
    private Paint paint = new Paint();
    private Bitmap bitmap;

    public DrawImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setAlign(Paint.Align align) {
        paint.setTextAlign(align);
    }

    public Paint.Align getAlign() {
        return paint.getTextAlign();
    }

    @Override
    public void drawOnCanvas(Canvas canvas, float x, float y) {
        canvas.drawBitmap(bitmap, getX(canvas, x), getY(y), paint);
    }

    private float getY(float y) {
        float baseline = -paint.ascent();
        return baseline + y;
    }

    private float getX(Canvas canvas, float x) {
        float xPos = x;
        if (paint.getTextAlign().equals(Paint.Align.CENTER)) {
            xPos += (canvas.getWidth() - bitmap.getWidth()) / 2;
        } else if (paint.getTextAlign().equals(Paint.Align.RIGHT)) {
            xPos += canvas.getWidth() - bitmap.getWidth();
        }
        return xPos;
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }
}
