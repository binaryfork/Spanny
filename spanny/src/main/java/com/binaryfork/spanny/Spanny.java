package com.binaryfork.spanny;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

/**
 * Spannable wrapper for simple creation of Spannable strings.
 */
public class Spanny extends SpannableStringBuilder {

    private int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

    public Spanny(String text) {
        super(text);
    }

    public Spanny(String text, Object span) {
        super(text);
        setSpan(span, 0, text.length());
    }

    public Spanny append(String text, Object... spans) {
        append(text);
        for (Object span : spans) {
            setSpan(span, length() - text.length(), length());
        }
        return this;
    }
    public Spanny append(String text, ImageSpan imageSpan) {
        text = "." + text;
        append(text);
        setSpan(imageSpan, length() - text.length(), length() - text.length() + 1);
        return this;
    }

    public Spanny appendText(String text) {
        append(text);
        return this;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private void setSpan(Object span, int start, int end) {
        setSpan(span, start, end, flag);
    }

    /**
     * Sets a span object to all appearances of specified text in the spannable.
     * A new instance of a span object must be provided for each iteration
     * because it can't be reused.
     *
     * @param textToSpan Case-sensitive text to span in the current spannable.
     * @param getSpan    Interface to get a span for each spanned string.
     * @return {@code Spanny}.
     */
    public Spanny findAndSpan(String textToSpan, GetSpan getSpan) {
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = toString().indexOf(textToSpan, lastIndex);
            if (lastIndex != -1) {
                setSpan(getSpan.getSpan(), lastIndex, lastIndex + textToSpan.length());
                lastIndex += textToSpan.length();
            }
        }
        return this;
    }

    /**
     * Interface to return a new span object when spanning multiple parts in the text.
     */
    public interface GetSpan {

        /**
         * @return A new span object should be returned.
         */
        Object getSpan();
    }

    public static SpannableString spanText(CharSequence text, Object span) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(span, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}