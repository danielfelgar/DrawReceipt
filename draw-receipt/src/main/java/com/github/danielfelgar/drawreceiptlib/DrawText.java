package com.github.danielfelgar.drawreceiptlib;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by daniel on 12/08/2016.
 */
public class DrawText implements IDrawItem {
    private Paint paint = new Paint();
    private String text;
    private boolean newLine;

    public DrawText(String text) {
        this.text = text;
    }

    @Override
    public void drawOnCanvas(Canvas canvas, float x, float y) {
        canvas.drawText(text, getX(canvas, x), getY(y), paint);
    }

    private float getY(float y) {
        float baseline = -paint.ascent();
        return baseline + y;
    }

    private float getX(Canvas canvas, float x) {
        float xPos = x;
        if (paint.getTextAlign().equals(Paint.Align.CENTER)) {
            xPos = (canvas.getWidth() / 2);
        } else if (paint.getTextAlign().equals(Paint.Align.RIGHT)) {
            xPos = canvas.getWidth();
        }
        return xPos;
    }

    @Override
    public int getHeight() {
        return (newLine ? (int) getTextSize() : 0);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return paint.getColor();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public float getTextSize() {
        return paint.getTextSize();
    }

    public void setTextSize(float textSize) {
        paint.setTextSize(textSize);
    }

    public void getTypeface() {
        paint.getTypeface();
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
    }

    public void setAlign(Paint.Align align) {
        paint.setTextAlign(align);
    }

    public Paint.Align getAlign() {
        return paint.getTextAlign();
    }

    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }

    public boolean getNewLine() {
        return newLine;
    }
}
