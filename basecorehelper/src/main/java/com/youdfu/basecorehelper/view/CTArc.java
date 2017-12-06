package com.youdfu.basecorehelper.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class CTArc implements Painter {
    private float radius;
    private float strokeWidth;
    private int cx;
    private int cy;
    private RectF mRectF = new RectF();
    private float startAngle;
    private float sweepAngle;
    public Paint circlePaint = new Paint();

    public float getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public float getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setPoint(int x, int y) {
        this.cx = x;
        this.cy = y;
    }

    public void setRectF(int x, int y, float radius) {
        mRectF.left = x - radius;
        mRectF.top = y - radius;
        mRectF.right = x + radius;
        mRectF.bottom = y + radius;
    }

    /**
     * @param x
     * @param y
     * @param innerRadius 内半径
     * @param outerRadius 外半径
     */
    public void setRectF(int x, int y, float innerRadius, float outerRadius) {
        float radius = (innerRadius + outerRadius) / 2f;

        mRectF.left = x - radius;
        mRectF.top = y - radius;
        mRectF.right = x + radius;
        mRectF.bottom = y + radius;

        circlePaint.setStrokeWidth(outerRadius - innerRadius);
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public CTArc() {
        circlePaint.setColor(Color.WHITE);
        circlePaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, circlePaint);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

    }
}
