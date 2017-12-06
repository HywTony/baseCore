package com.youdfu.basecorehelper.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MiUiUtils {
    public static Boolean isAboveMiUi6;

    /**
     * 获取系统属性,判断是否是miui6
     */
    public static boolean isAboveMiUi6() {
        if (isAboveMiUi6 != null) {
            return isAboveMiUi6;
        }
        String ss = SystemUtils.getSystemProperty("ro.miui.ui.version.name");
        if (ss != null && (ss.equals("V6") || ss.equals("V7"))) {
            isAboveMiUi6 = true;
        }
        isAboveMiUi6 = false;
        return isAboveMiUi6;
    }

    /**
     * 设置沉浸式黑色的状态栏 AppTheme
     */
    public static void setDarkTransparentBar(Activity ctx, int AppTheme) {
        if (isAboveMiUi6()) {
            int theme = ctx.getSharedPreferences("cons", Context.MODE_PRIVATE).getInt(
                    "theme", AppTheme);
            ctx.setTheme(theme);
            StatusBarSetting(ctx);
        }
    }

    public static void StatusBarSetting(Activity context) {
        Window window = context.getWindow();
        Class clazz = window.getClass();

        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class
                    .forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams
                    .getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);
            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class,
                    int.class);
            // //状态栏透明
            // extraFlagField.invoke(window, tranceFlag, tranceFlag);
            // //状态栏darkMode
            // extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            // 即透明又darkMode
            extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag
                    | darkModeFlag);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
