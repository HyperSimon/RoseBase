package com.roselism.rosebase.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntegerRes;

/**
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/13 22:04
 * @packageName: com.roselism.mobilesafe24.util
 */
public class SPUtil {

    public static String CONFIG_NAME = "config";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    /**
     * @param context
     * @param key
     * @param value
     * @deprecated 使用putString代替
     */
    public static void writeString(Context context, String key, String value) {
        // 相同 则进行存储
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value); // 储存密码
        editor.apply();
    }

    /**
     * 写入并且返回当前的状态
     *
     * @param context
     * @param key
     * @param value
     * @return 返回这个数据当前的最新数据
     */
    public static void putString(Context context, String key, String value) {
        // 相同 则进行存储
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value); // 储存密码
        editor.apply();
    }

    /**
     * 获取boolean值
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getSharedPreferences(context).getBoolean(key, true);
    }

    /**
     * 获取boolean值
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * 写入并且返回当前的状态
     *
     * @param context
     * @param key
     * @param value
     * @return 返回这个数据当前的最新数据
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        // 相同 则进行存储
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value); // 储存密码
        editor.apply();
        return getBoolean(context, key);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value); // 储存密码
        editor.apply();
    }

    @IntegerRes
    public static int getInt(Context context, String key, int defaultInt) {
        return getSharedPreferences(context).getInt(key, defaultInt);
    }
}
