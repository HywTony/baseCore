package com.youdfu.basecorehelper.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.mindpipe.android.logging.log4j.LogConfigurator;

public class HelperUtil {
    private static Logger log = Logger.getLogger("APP");
    public static String debugFile;
    public static Context applicationContext;
    public static boolean useApplicationPath = true;

    public static void initialize(Context context) {
        initialize(context, true);
    }

    public static void initialize(Context context, String DebugFile, boolean crashOpen) {
        debugFile = DebugFile;
        initialize(context, crashOpen);
    }

    public static void initialize(Context context, boolean crashOpen) {
        //异常监控
        if (crashOpen) {
            new CrashHandler().setOnCrash(context, new CrashHandler.OnCrash() {
                @Override
                public void crash(String messag) {
                    log.error(messag);
                }
            });
        }
        applicationContext = context;
        initLog4j();
    }

    public static Context getApplicationContext() {
        if (applicationContext == null) {

        } else {

        }
        return applicationContext;
    }

    /**
     * 日志配置
     */
    private static void initLog4j() {
        try {
            if (debugFile == null) {
                String applicationName = getApplicationName();
                if (applicationName == null) {
                    applicationName = "AppLog";
                }

                debugFile = (useApplicationPath ?
                        getApplicationContext().getFilesDir().getAbsolutePath()
                        : Environment.getExternalStorageDirectory().getPath())
                        + "/" + applicationName + "Log.txt";
            }

            final LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setFileName(debugFile);
            logConfigurator.setRootLevel(Level.INFO);
            logConfigurator.setLevel("org.apache", Level.INFO);
            logConfigurator.setFilePattern("%d{HH:mm:ss} %-5p[%c{2}] %m%n");
            logConfigurator.setMaxFileSize(1024 * 1024 * 1); //1M的记录空间
            logConfigurator.setImmediateFlush(true);
            logConfigurator.setUseFileAppender(true);
            logConfigurator.setMaxBackupSize(1);
            logConfigurator.configure();
        } catch (Exception e) {

        }
    }


    public static String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = "APP";
        if (applicationInfo != null) {
            applicationName =
                    (String) packageManager.getApplicationLabel(applicationInfo);
        }

        return applicationName;
    }

}
