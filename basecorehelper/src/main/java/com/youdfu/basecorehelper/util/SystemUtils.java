package com.youdfu.basecorehelper.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SystemUtils {
    /**
     * 检查网络状态
     *
     * @return
     */
    public static boolean checkWifi(Context context) {
        boolean isWifiConnect = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for (int i = 0; i < networkInfos.length; i++) {
            if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                if (networkInfos[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                    isWifiConnect = false;
                }
                if (networkInfos[i].getType() == ConnectivityManager.TYPE_WIFI) {
                    isWifiConnect = true;
                }
            }
        }
        return isWifiConnect;
    }

    /**
     * 检查网络是否连接
     *
     * @return
     */
    public static boolean networkStatusOK(Context context) {
        boolean netStatus = false;
        try {
            ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()
                        && activeNetworkInfo.isConnected()) {
                    netStatus = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return netStatus;
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

    /**
     * @param propName
     * @return
     */
    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e("send", "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e("send", "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    public static String getSystemInfo(Context context) {
        String systeminfo = "";
        PackageManager manager = context
                .getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context
                    .getPackageName(), 0);
            StringBuffer platform = new StringBuffer("android/");
            platform.append(android.os.Build.VERSION.RELEASE).append("/");
            platform.append("v").append(info.versionCode).append("/");
            platform.append(android.os.Build.BRAND).append("/");
            platform.append(android.os.Build.MODEL).append("/");

            systeminfo = platform.toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return systeminfo;
    }

    public static boolean checkGoogleMap(Context context) {
        boolean isInstallGMap = false;
        List<PackageInfo> packs = context
                .getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (p.versionName == null) { // system packages
                continue;
            }
            if ("com.google.android.apps.maps".equals(p.packageName)) {
                isInstallGMap = true;
                break;
            }
        }
        return isInstallGMap;
    }

    /**
     * 获取版本签名
     *
     * @param cxt
     * @return
     */
    public static String getAppSignatureMD5String(Context cxt) {
        List<PackageInfo> apps = cxt.getPackageManager().getInstalledPackages(
                PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();
        while (iter.hasNext()) {
            PackageInfo info = iter.next();
            String packageName = info.packageName;
            if (packageName.equals(cxt.getPackageName())) {
                return MD5(info.signatures[0].toByteArray()).toLowerCase(
                        Locale.US);
            }
        }
        return null;
    }

    public static String MD5(byte[] btInput) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取系统属性,判断是否是miui6
     */
    public static boolean isMiui6() {
        String ss = SystemUtils.getSystemProperty("ro.miui.ui.version.name");
        if (ss != null && ss.equals("V6")) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否是主进程
     *
     * @return
     */
    public static boolean isMainProcess(Context context, String packageName) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                if (appProcess.processName.equalsIgnoreCase(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
//
//    public static void setStatusBar(Activity context, int color) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(context, true);
//        }
//
//        SystemBarTintManager tintManager = new SystemBarTintManager(context);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintColor(color);//通知栏所需颜色
//    }
//
//    @TargetApi(19)
//    private static void setTranslucentStatus(Activity context, boolean on) {
//        Window win = context.getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }
}
