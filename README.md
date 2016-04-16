# Spanny
A helper class that extends SpannableStringBuilder and adds methods to easily mark the text with multiple spans.

![example](http://i.imgur.com/NPnl0yy.png?1)

### Download
You can just [copy the class][1] to your project or grab it via Gradle:
```
compile 'com.binaryfork:spanny:1.0.4'
```

### Usage
Use `.append(text, span)` to add and mark the text with any span:
```java
Spanny spanny = new Spanny("Underline text", new UnderlineSpan())
                .append("\nRed text", new ForegroundColorSpan(Color.RED))
                .append("\nPlain text");
textView.setText(spanny);
```
Mark the text with multiple spans: 
```java
spanny.append("Blue underlined text", new ForegroundColorSpan(Color.BLUE), new UnderlineSpan());
```
If you need a single SpannableString you can use a static method `.spanText`:
```java
textView.setText(Spanny.spanText("Underline text", new UnderlineSpan()));
```
Find and span multiple appearences of a string:
```java
Spanny spanny = new Spanny("All 'a' will be red.")
spanny.findAndSpan("a", new Spanny.GetSpan() {
            @Override public Object getSpan() {
                return new ForegroundColorSpan(Color.RED);
            }
        });
```
 
Example
--------
List of all available spans: [http://developer.android.com/reference/android/text/style/package-summary.html][3]

Check the [sample app][2] for custom spannables.

You can easily make a text with over 20 styles in a single TextView:

```java
Spanny spanny = new Spanny("StyleSpan", new StyleSpan(Typeface.BOLD_ITALIC))
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
```

Feel free to pull request a custom spannable.

License
--------

    Copyright 2015 Pavlovsky Ivan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 [1]: https://github.com/binaryfork/Spanny/blob/master/spanny/src/main/java/com/binaryfork/spanny/Spanny.java
 [2]: https://github.com/binaryfork/Spanny/blob/master/app/src/main/java/com/binaryfork/spannysample/
 [3]: http://developer.android.com/reference/android/text/style/package-summary.html
