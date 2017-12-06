package com.youdfu.basecorehelper.view;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;

public class SpannableText extends SpannableString {
    private CharSequence text;


    public SpannableText(CharSequence source) {
        super(source);
        text = source;
    }

    public SpannableText setColor(int color) {
        setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableText setTextSize(int size) {
        setSpan(new AbsoluteSizeSpan(size), 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableText setUnderlineSpan() {
        setSpan(new UnderlineSpan(), 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableText setTypeFace(Typeface typeFace) {
        TypefaceSpan robotoRegularSpan = new CustomTypefaceSpan("", typeFace);
        setSpan(robotoRegularSpan, 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return this;
    }
}
