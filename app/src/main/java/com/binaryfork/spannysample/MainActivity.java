package com.binaryfork.spannysample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        getActionBar().setTitle(Spanny.spanText("Spanny", new CustomTypefaceSpan(typeface)));
        TextView textView = (TextView) findViewById(R.id.textView);
        Spanny spanny = new Spanny("StyleSpan\n", new StyleSpan(Typeface.BOLD_ITALIC))
                .append("CustomTypefaceSpan", new CustomTypefaceSpan(typeface))
                .append("CustomAlignmentSpan", new CustomAlignmentSpan(CustomAlignmentSpan.RIGHT_TOP))
                .append("\nUnderlineSpan, ", new UnderlineSpan())
                .append(" TypefaceSpan, ", new TypefaceSpan("serif"))
                .append("URLSpan, ", new URLSpan("google.com"))
                .append("StrikethroughSpan", new StrikethroughSpan())
                .append("\nQuoteSpan", new QuoteSpan(Color.RED))
                .append("\nPlain text")
                .append("SubscriptSpan", new SubscriptSpan())
                .append("SuperscriptSpan", new SuperscriptSpan())
                .append("\n\nBackgroundSpan", new BackgroundColorSpan(Color.LTGRAY))
                .append("\n\nCustomBackgroundSpan", new CustomBackgroundSpan(Color.DKGRAY, dp(16)))
                .append("\n\nForegroundColorSpan", new ForegroundColorSpan(Color.LTGRAY))
                .append("\nAlignmentSpan", new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER))
                .append("\nTextAppearanceSpan\n", new TextAppearanceSpan(this, android.R.style.TextAppearance_Medium))
                .append("ImageSpan", new ImageSpan(getApplicationContext(), R.mipmap.ic_launcher))
                .append("\nRelativeSizeSpan", new RelativeSizeSpan(1.5f))
                .append("\n\nMultiple spans", new StyleSpan(Typeface.ITALIC), new UnderlineSpan(),
                        new TextAppearanceSpan(this, android.R.style.TextAppearance_Large),
                        new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), new BackgroundColorSpan(Color.LTGRAY));
        textView.setText(spanny);

        spanny = new Spanny("\n\nFind and span the word. All appearances of the word will be spanned.");
        spanny.findAndSpan("word", new Spanny.GetSpan() {
            @Override public Object getSpan() {
                return new UnderlineSpan();
            }
        });
        textView.append(spanny);
    }

    private int dp(int value) {
        return (int) Math.ceil(getResources().getDisplayMetrics().density * value);
    }
}
