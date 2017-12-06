package com.youdfu.basecorehelper.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtils {
	
	public static final String SharedPreferencesDefaultName="LocalPars";
	
	public static String getString(String name){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		return sp.getString(name, null);
	}
	
	public static boolean saveString(String name, String value){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		Editor edit=sp.edit();
		edit.putString(name, value);
		return edit.commit();
	}
	public static boolean contains(String name){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		return sp.contains(name);
	}
	public static boolean remove(String name){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		Editor edit=sp.edit();
		edit.remove(name);
		return edit.commit();
	}
	public static boolean saveBoolean(String name, boolean value){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		Editor edit=sp.edit();
		edit.putBoolean(name, value);
		return edit.commit();
	}
	public static boolean getBooleanDefalse(String name){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		return sp.getBoolean(name, false);
	}
	public static boolean getBooleanDetrue(String name){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		return sp.getBoolean(name, true);
	}
	//整数
	public static boolean saveInt(String name, int value){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		Editor edit=sp.edit();
		edit.putInt(name, value);
		return edit.commit();
	}
	public static int getInt(String name, int value){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		return sp.getInt(name, value);
	}
	//float
	public static boolean saveFloat(String name, float value){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		Editor edit=sp.edit();
		edit.putFloat(name, value);
		return edit.commit();
	}
	public static Float getFloat(String name, float value){
		Context cxt= HelperUtil.getApplicationContext();
		SharedPreferences sp=cxt.getSharedPreferences(SharedPreferencesDefaultName, Context.MODE_PRIVATE);

		return sp.getFloat(name, value);
	}
}
