package com.ay3524.mvvmlearn.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static SharedPreferences mSharedPref;

    private SharedPref() {
    }

    public static synchronized SharedPreferences getInstance(Context context) {
        if (mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
        return mSharedPref;
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    /**
     * Enquires {@link SharedPreferences} to check whether a given Key exists in it or not.
     *
     * @param key One of key Belonging to {@link SharedPrefsKey}.
     * @return {@code true} if the key is present in {@link SharedPreferences}, else {@code false}.
     */
    public boolean contains(String key) {
        return mSharedPref.contains(key);
    }

    public void deleteKey(String key) {
        mSharedPref.edit().remove(key).apply();
    }

    public static void clearAll() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.clear();
        editor.apply();
    }
}


