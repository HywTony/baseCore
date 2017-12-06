package com.youdfu.basecorehelper.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextPaint;

public class CTText implements Painter {
    public enum Position {
        LEFTTOP(0),
        CENTER(1),
        LEFTBOTTOM(2),
        CENTERTOP(3),
        RIGHTTOP(4),
        RIGHTBOTTOM(5),
        RIGHTCENTER(6);

        Position(int nativeInt) {
            this.nativeInt = nativeInt;
        }

        final int nativeInt;
    }

    private int color;
    public TextPaint mTextPaint = new TextPaint();
    private String text = "29.2";
    private float textSize = 50;
    private float mTextWidth;
    private float mTextHeight;
    private Position align = Position.CENTER;
    private Point p = new Point();

    public void setPoint(int x, int y) {
        p.x = x;
        p.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        mTextPaint.setColor(color);
    }

    public void setTypeface(Typeface typeface) {
        mTextPaint.setTypeface(typeface);
    }


    public Typeface getTypeface() {
        return mTextPaint.getTypeface();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setAlign(Position align) {
        this.align = align;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        mTextPaint.setTextSize(textSize);
    }

    public CTText() {
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextSize(textSize);
    }

    public void invalidateTextPaintAndMeasurements() {
        mTextHeight = -mTextPaint.getFontMetrics().ascent * 0.8f;
//        mTextHeight = -mTextPaint.getFontMetrics().ascent;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        invalidateTextPaintAndMeasurements();
    }


    @Override
    public void onDraw(Canvas canvas) {
        switch (align) {
            case CENTER:
                canvas.drawText(text,
                        p.x - mTextPaint.measureText(text) / 2,
                        p.y + mTextHeight / 2,
                        mTextPaint);
                break;
            case LEFTTOP:
                canvas.drawText(text,
                        p.x,
                        p.y + mTextHeight,
                        mTextPaint);
                break;
            case LEFTBOTTOM:
                canvas.drawText(text,
                        p.x,
                        p.y,
                        mTextPaint);
                break;
            case CENTERTOP:
                canvas.drawText(text,
                        p.x - mTextPaint.measureText(text) / 2,
                        p.y + mTextHeight,
                        mTextPaint);
                break;
            case RIGHTTOP:
                canvas.drawText(text,
                        p.x - mTextPaint.measureText(text),
                        p.y + mTextHeight,
                        mTextPaint);
                break;
            case RIGHTBOTTOM:
                canvas.drawText(text,
                        p.x - mTextPaint.measureText(text),
                        p.y,
                        mTextPaint);
                break;
            case RIGHTCENTER:
                canvas.drawText(text,
                        p.x - mTextPaint.measureText(text),
                        p.y + mTextHeight / 2,
                        mTextPaint);
                break;
        }
    }
}
