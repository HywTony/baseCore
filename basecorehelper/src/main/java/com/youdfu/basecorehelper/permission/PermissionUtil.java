package com.youdfu.basecorehelper.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.youdfu.basecorehelper.R;

import rx.Observable;

public class PermissionUtil {
    public static Observable<Boolean> checkLocation(Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        AskFor_ACCESS_FINE_LOCATION_Permission(context);
                    }
                })
                .filter(aBoolean -> aBoolean);
    }

    public static Observable<Boolean> checkCamera(Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.CAMERA)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        AskFor_CAMERA_Permission(context);
                    }
                })
                .filter(aBoolean -> aBoolean);
    }

    public static Observable<Boolean> checkStorage(Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        AskFor_WRITE_EXTERNAL_STORAGE_Permission(context);
                    }
                })
                .filter(aBoolean -> aBoolean);
    }

    public static Observable<Boolean> checkAudio(Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.RECORD_AUDIO)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        AskFor_RECORD_AUDIO_Permission(context);
                    }
                })
                .filter(aBoolean -> aBoolean);
    }

    public static Observable<Boolean> checkOutgoingCall(Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.PROCESS_OUTGOING_CALLS)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        AskFor_PROCESS_OUTGOING_CALLS_Permission(context);
                    }
                })
                .filter(aBoolean -> aBoolean);
    }

    public static Observable<Boolean> checkAlertWindow(Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.SYSTEM_ALERT_WINDOW)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        AskFor_ACTION_MANAGE_OVERLAY_PERMISSION_Permission(context);
                    }
                })
                .filter(aBoolean -> aBoolean);
    }

    public static boolean checkPermission(Context context, String... permisssions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        boolean allPermision = true;

        for (String permission : permisssions)
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                allPermision = false;
                break;
            }
        return allPermision;
    }

    public static void AskFor_CAMERA_Permission(final Context context) {
        AskFor_Permission(context, context.getString(R.string.pemission_camera));
    }

    public static void AskFor_ACCESS_FINE_LOCATION_Permission(final Context context) {
        AskFor_Permission(context, context.getString(R.string.permission_loc));
    }

    public static void AskFor_WRITE_EXTERNAL_STORAGE_Permission(final Context context) {
        AskFor_Permission(context, context.getString(R.string.permission_storage));
    }

    public static void AskFor_RECORD_AUDIO_Permission(final Context context) {
        AskFor_Permission(context, context.getString(R.string.permission_audio));
    }

    public static void AskFor_PROCESS_OUTGOING_CALLS_Permission(final Context context) {
        AskFor_Permission(context, context.getString(R.string.permission_outgoing_calls));
    }

    public static void AskFor_ACTION_MANAGE_OVERLAY_PERMISSION_Permission(final Context context) {
        //悬浮窗权限没有获取
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                String message = context.getString(R.string.permission_overlay);
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + context.getPackageName()));
                AskFor_Permission(context, message, intent);
            }
        }
    }

    public static void AskFor_Permission(final Context context, String message) {
        //打开权限设置的页面
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);

        AskFor_Permission(context, message, intent);
    }

    public static void AskFor_Permission(final Context context, String message, final Intent intent) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.permission_need))
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.permission_setting), (dialog, which) -> {
                    context.startActivity(intent);
                })
                .setNegativeButton(context.getString(R.string.permission_cancel), null)
                .show();
    }
}
