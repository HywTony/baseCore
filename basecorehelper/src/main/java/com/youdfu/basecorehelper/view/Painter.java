package com.youdfu.basecorehelper.view;

import android.graphics.Canvas;

public interface Painter {
    void onSizeChanged(int w, int h, int oldw, int oldh);

    void onDraw(Canvas canvas);
}
