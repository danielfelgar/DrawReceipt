package com.github.danielfelgar.drawreceiptlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 12/08/2016.
 */
public class ReceiptBuilder {
    List<IDrawItem> listItens = new ArrayList<>();
    private int backgroundColor = Color.WHITE;
    private float textSize;
    private int color = Color.BLACK;
    private int width;
    private int marginTop, marginBottom, marginLeft, marginRight;
    private Typeface typeface;
    private Paint.Align align = Paint.Align.LEFT;

    public ReceiptBuilder(int width) {
        this.width = width;
    }

    public ReceiptBuilder setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public ReceiptBuilder setBackgroudColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public ReceiptBuilder setColor(int color) {
        this.color = color;
        return this;
    }

    public ReceiptBuilder setTypeface(Context context, String typefacePath) {
        typeface = Typeface.createFromAsset(context.getAssets(), typefacePath);
        return this;
    }

    public ReceiptBuilder setDefaultTypeface() {
        typeface = null;
        return this;
    }

    public ReceiptBuilder setAlign(Paint.Align align) {
        this.align = align;
        return this;
    }

    public ReceiptBuilder setMargin(int margin) {
        this.marginLeft = margin;
        this.marginRight = margin;
        this.marginTop = margin;
        this.marginBottom = margin;
        return this;
    }

    public ReceiptBuilder setMargin(int marginTopBottom, int marginLeftRight) {
        this.marginLeft = marginLeftRight;
        this.marginRight = marginLeftRight;
        this.marginTop = marginTopBottom;
        this.marginBottom = marginTopBottom;
        return this;
    }

    public ReceiptBuilder setMarginLeft(int margin) {
        this.marginLeft = margin;
        return this;
    }

    public ReceiptBuilder setMarginRight(int margin) {
        this.marginRight = margin;
        return this;
    }

    public ReceiptBuilder setMarginTop(int margin) {
        this.marginTop = margin;
        return this;
    }

    public ReceiptBuilder setMarginBottom(int margin) {
        this.marginBottom = margin;
        return this;
    }

    public ReceiptBuilder addText(String text) {
        return addText(text, true);
    }

    public ReceiptBuilder addText(String text, Boolean newLine) {
        DrawText drawerText = new DrawText(text);
        drawerText.setTextSize(this.textSize);
        drawerText.setColor(this.color);
        drawerText.setNewLine(newLine);
        if (typeface != null) {
            drawerText.setTypeface(typeface);
        }
        if (align != null) {
            drawerText.setAlign(align);
        }
        listItens.add(drawerText);
        return this;
    }

    public ReceiptBuilder addImage(Bitmap bitmap) {
        DrawImage drawerImage = new DrawImage(bitmap);
        if (align != null) {
            drawerImage.setAlign(align);
        }
        listItens.add(drawerImage);
        return this;
    }

    public ReceiptBuilder addItem(IDrawItem item) {
        listItens.add(item);
        return this;
    }

    public ReceiptBuilder addBlankSpace(int heigth) {
        listItens.add(new DrawBlankSpace(heigth));
        return this;
    }

    public ReceiptBuilder addParagraph() {
        listItens.add(new DrawBlankSpace((int) textSize));
        return this;
    }

    public ReceiptBuilder addLine() {
        return addLine(width - marginRight - marginLeft);
    }

    public ReceiptBuilder addLine(int size) {
        DrawLine line = new DrawLine(size);
        line.setAlign(align);
        line.setColor(color);
        listItens.add(line);
        return this;
    }

    private int getHeight() {
        int height = 5 + marginTop + marginBottom;
        for (IDrawItem item : listItens) {
            height += item.getHeight();
        }
        return height;
    }

    private Bitmap drawImage() {
        Bitmap image = Bitmap.createBitmap(width - marginRight - marginLeft, getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawColor(backgroundColor);
        float size = marginTop;
        for (IDrawItem item : listItens) {
            item.drawOnCanvas(canvas, 0, size);
            size += item.getHeight();
        }
        return image;
    }

    public Bitmap build() {
        Bitmap image = Bitmap.createBitmap(width, getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        Paint paint = new Paint();
        canvas.drawColor(backgroundColor);
        canvas.drawBitmap(drawImage(), marginLeft, 0, paint);
        return image;
    }

}
