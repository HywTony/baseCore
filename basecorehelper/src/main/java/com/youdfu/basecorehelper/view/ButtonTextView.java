package com.youdfu.basecorehelper.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;


public class ButtonTextView extends android.support.v7.widget.AppCompatTextView {
    public ButtonTextView(Context context) {
        super(context);
    }

    public ButtonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Drawable[] drawables;
        Drawable bg;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawables = getCompoundDrawables();
                for (Drawable drawable : drawables) {
                    if (drawable != null)
                        drawable.setAlpha(125);
                }
                bg = getBackground();
                if (bg != null) {
                    bg.setAlpha(125);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                drawables = getCompoundDrawables();
                for (Drawable drawable : drawables) {
                    if (drawable != null)
                        drawable.setAlpha(255);
                }
                bg = getBackground();
                if (bg != null) {
                    bg.setAlpha(255);
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
