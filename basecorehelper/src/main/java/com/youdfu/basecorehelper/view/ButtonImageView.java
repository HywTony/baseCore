package com.youdfu.basecorehelper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by kexuebiao on 16/4/8.
 */
public class ButtonImageView extends ImageView {
    public ButtonImageView(Context context) {
        super(context);
    }

    public ButtonImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.5f);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setAlpha(1f);
                break;
        }

        return super.onTouchEvent(event);
    }
}
