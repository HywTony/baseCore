package com.youdfu.basecorehelper.util;

import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

public class DensityUtil {
    private static float density = -1000;
    public static int statusBarH = -1000;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5f);
    }

    public static float getDensity() {
        if (density <= 0) {
            density = HelperUtil.getApplicationContext().getResources().getDisplayMetrics().density;
        }
        return density;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenW() {
        return HelperUtil.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getUISize(float size) {
        return dip2px(size / 3);
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH() {
        return HelperUtil.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 状态栏高度
     */
    public static int statusBarHeight() {
        if (statusBarH <= 0) {
            Class<?> c;
            Object obj;
            Field field;
            int x;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarH = HelperUtil.getApplicationContext().getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return statusBarH;
    }

    /**
     * 获取文字的宽度
     *
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    /**
     * 获取文字的高度
     *
     * @param paint
     * @return
     */
    public static int getFontHeight(Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    public static void reSize(ViewGroup.LayoutParams lp, int constint,
                              int height, int width) {
        int thumbnailHeight = 200, thumbnailWidth = 200;
        thumbnailHeight = height;
        thumbnailWidth = width;
        if (thumbnailHeight > constint || thumbnailWidth > constint) {
            if (thumbnailHeight > thumbnailWidth) {
                thumbnailWidth = thumbnailWidth * constint / thumbnailHeight;
                thumbnailHeight = constint;
            } else {
                thumbnailHeight = thumbnailHeight * constint / thumbnailWidth;
                thumbnailWidth = constint;
            }
        } else {
            thumbnailHeight = thumbnailHeight * constint / thumbnailWidth;
            thumbnailWidth = constint;
        }
        lp.height = thumbnailHeight;
        lp.width = thumbnailWidth;
    }


    /**
     * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
     *
     * @param view
     * @return
     */
    public static int getViewMeasuredHeight(View view) {
//        int height = view.getMeasuredHeight();
//        if(0 < height){
//            return height;
//        }
        calcViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 测量控件的尺寸
     *
     * @param view
     */
    public static void calcViewMeasure(View view) {
//        int width = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//        int height = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//        view.measure(width,height);

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
    }

}