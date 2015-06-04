package com.binaryfork.spannysample;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * Additional spans that don't extend MetricAffectingSpan will be not apply.
 * ReplacementSpan issue http://stackoverflow.com/questions/28323901/foregroundcolorspan-is-not-applied-to-replacementspan
 *
 * setMovementMethod(LinkMovementMethod.getInstance()) is breaking this span.
 */
public class CustomAlignmentSpan extends ReplacementSpan {

    private int color = -1;
    private float x;
    private float y;
    private boolean xIsTextEnd;

    public CustomAlignmentSpan(float x, float y, boolean xIsTextEnd) {
        this.x = x;
        this.y = y;
        this.xIsTextEnd = xIsTextEnd;
    }

    public CustomAlignmentSpan(int color, float x, float y, boolean xIsTextEnd) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.xIsTextEnd = xIsTextEnd;
    }

    @Override public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return 0;
    }

    @Override public void draw(Canvas canvas, CharSequence text, int start, int end, float defaultX, int top, int defaultY, int bottom, Paint paint) {
        if (color != -1)
            paint.setColor(color);
        if (xIsTextEnd)
            x = x - paint.measureText(text, start, end);
        canvas.drawText(text, start, end, x,
                paint.getTextSize() + y, paint);
    }
}
