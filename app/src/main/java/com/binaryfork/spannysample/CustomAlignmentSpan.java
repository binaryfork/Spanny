package com.binaryfork.spannysample;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * Additional spans that don't extend MetricAffectingSpan will be not apply.
 * ReplacementSpan issue http://stackoverflow.com/questions/28323901/foregroundcolorspan-is-not-applied-to-replacementspan
 * <p/>
 * setMovementMethod(LinkMovementMethod.getInstance()) is breaking this span.
 */
public class CustomAlignmentSpan extends ReplacementSpan {

    public final static int RIGHT_TOP = 1;
    public final static int RIGHT_CENTER = 2;
    public final static int RIGHT_BOTTOM = 3;

    private int color = -1;
    private int position;

    public CustomAlignmentSpan(int position) {
        this.position = position;
    }

    public CustomAlignmentSpan(int position, int color) {
        this.position = position;
        this.color = color;
    }

    @Override public int getSize(Paint paint, CharSequence text, int start, int end, Paint.
            FontMetricsInt fm) {
        return 0;
    }

    @Override public void draw(Canvas canvas, CharSequence text, int start, int end,
                               float defaultX, int top, int defaultY, int bottom, Paint paint) {
        float x = 0;
        float y = 0;
        switch (position) {
            case RIGHT_TOP:
                x = canvas.getWidth() - paint.measureText(text, start, end);
                y = paint.getTextSize();
                break;
            case RIGHT_CENTER:
                x = canvas.getWidth() - paint.measureText(text, start, end);
                y = canvas.getHeight() / 2;
                break;
            case RIGHT_BOTTOM:
                x = canvas.getWidth() - paint.measureText(text, start, end);
                y = canvas.getHeight() - paint.getFontMetricsInt().bottom;
                break;
        }
        if (color != -1)
            paint.setColor(color);
        canvas.drawText(text, start, end, x, y, paint);
    }
}