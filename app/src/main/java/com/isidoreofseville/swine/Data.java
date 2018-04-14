package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dave on 2/7/2018.
 */

public class Data {

    public static void writeString(Activity context, final String KEY, int property) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(KEY, property);
        editor.commit();
    }

    public static int readString(Activity context, final String KEY) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int data = sharedPref.getInt(KEY, 0);
        return data;
    }
}
