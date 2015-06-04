package com.binaryfork.spannysample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class CustomBackgroundSpan extends ReplacementSpan {

    private int color;
    private int corner;

    public CustomBackgroundSpan(int color, int corner) {
        this.color = color;
        this.corner = corner;
    }

    @Override public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.getTextSize());
    }

    @Override public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint.setColor(color);
        RectF rect = new RectF(x , top, x + paint.measureText(text, start, end) + corner, bottom);
        canvas.drawRoundRect(rect, corner, corner, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, start, end, x + corner/2, y, paint);
    }
}
