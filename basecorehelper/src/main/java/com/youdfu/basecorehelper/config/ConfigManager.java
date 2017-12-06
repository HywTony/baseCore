package com.youdfu.basecorehelper.config;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class ConfigManager {
    private final static String TAG = ConfigManager.class.getSimpleName();

    private final static String PREF_NAME = "config";
    private static Context sContext;
    private static HashMap<String, SharedPreferences> sPreferencesMap = new HashMap();

    public static void init(Context context) {
        sContext = context;
    }

    public static boolean contains(String key) {
        return contains(key, PREF_NAME);
    }

    public static boolean contains(String key, String preferencesKey) {
        return getPreferences(preferencesKey).contains(key);
    }

    public static void clear(String preferencesKey) {
        getPreferences(preferencesKey).edit().clear().apply();
    }

    public static void remove(String key) {
        remove(key, PREF_NAME);
    }

    public static void remove(String key, String preferencesKey) {
        getPreferences(preferencesKey).edit().remove(key).commit();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return getString(key, defaultValue, PREF_NAME);
    }

    public static String getString(String key, String defaultValue, String preferencesKey) {
        return getPreferences(preferencesKey).getString(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getBoolean(key, defaultValue, PREF_NAME);
    }

    public static boolean getBoolean(String key, boolean defaultValue, String preferencesKey) {
        return getPreferences(preferencesKey).getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return getInt(key, defaultValue, PREF_NAME);
    }

    public static int getInt(String key, int defaultValue, String preferencesKey) {
        return getPreferences(preferencesKey).getInt(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return getLong(key, defaultValue, PREF_NAME);
    }

    public static long getLong(String key, long defaultValue, String preferencesKey) {
        return getPreferences(preferencesKey).getLong(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return getFloat(key, defaultValue, PREF_NAME);
    }

    public static float getFloat(String key, float defaultValue, String preferencesKey) {
        return getPreferences(preferencesKey).getFloat(key, defaultValue);
    }

    public static void putString(String key, String value) {
        putString(key, value, PREF_NAME);
    }

    public static void putString(String key, String value, String preferencesKey) {
        putString(key, value, preferencesKey, true);
    }

    public static void putString(String key, String value, String preferencesKey, boolean async) {
        if (async) {
            getPreferences(preferencesKey).edit().putString(key, value).apply();
        } else {
            getPreferences(preferencesKey).edit().putString(key, value).commit();
        }
    }

    public static void putBoolean(String key, boolean value) {
        putBoolean(key, value, PREF_NAME);
    }

    public static void putBoolean(String key, boolean value, String preferencesKey) {
        putBoolean(key, value, preferencesKey, true);
    }

    public static void putBoolean(String key, boolean value, String preferencesKey, boolean async) {
        if (async) {
            getPreferences(preferencesKey).edit().putBoolean(key, value).apply();
        } else {
            getPreferences(preferencesKey).edit().putBoolean(key, value).commit();
        }
    }

    public static void putInt(String key, int value) {
        putInt(key, value, PREF_NAME);
    }

    public static void putInt(String key, int value, String preferencesKey) {
        putInt(key, value, preferencesKey, true);
    }

    public static void putInt(String key, int value, String preferencesKey, boolean async) {
        if (async) {
            getPreferences(preferencesKey).edit().putInt(key, value).apply();
        } else {
            getPreferences(preferencesKey).edit().putInt(key, value).commit();
        }
    }

    public static void putLong(String key, long value) {
        putLong(key, value, PREF_NAME);
    }

    public static void putLong(String key, long value, String preferencesKey) {
        putLong(key, value, preferencesKey, true);
    }

    public static void putLong(String key, long value, String preferencesKey, boolean async) {
        if (async) {
            getPreferences(preferencesKey).edit().putLong(key, value).apply();
        } else {
            getPreferences(preferencesKey).edit().putLong(key, value).commit();
        }
    }

    public static void putFloat(String key, float value) {
        putFloat(key, value, PREF_NAME);
    }

    public static void putFloat(String key, float value, String preferencesKey) {
        putFloat(key, value, preferencesKey, true);
    }

    public static void putFloat(String key, float value, String preferencesKey, boolean async) {
        if (async) {
            getPreferences(preferencesKey).edit().putFloat(key, value).apply();
        } else {
            getPreferences(preferencesKey).edit().putFloat(key, value).commit();
        }
    }

    public static SharedPreferences getPreferences(String key) {
        if (sPreferencesMap.containsKey(key)) {
            return (SharedPreferences) sPreferencesMap.get(key);
        }
        SharedPreferences preferences = sContext.getSharedPreferences(key, 0);
        sPreferencesMap.put(key, preferences);
        return preferences;
    }
}
