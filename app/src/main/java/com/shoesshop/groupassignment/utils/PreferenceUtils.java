package com.shoesshop.groupassignment.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    public static void saveStringSharedPreference(Context context, String key, String value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.commit();
        }catch (Exception e){

        }
    }

    public static String getStringSharedPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        String defaultValue = "";
        return prefs.getString(key, defaultValue);
    }

    public static void removeStringSharedPreference(Context context, String key) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        }catch (Exception e){

        }
    }

    public static void saveIntSharedPreference(Context context, String key, int value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key, value);
            editor.commit();
        }catch (Exception e){

        }
    }

    public static int getIntSharedPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        int defaultValue = 0;
        return prefs.getInt(key, defaultValue);
    }

    public static void removeIntSharedPreference(Context context, String key) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        }catch (Exception e){

        }
    }
}
