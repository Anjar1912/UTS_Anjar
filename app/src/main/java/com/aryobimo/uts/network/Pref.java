package com.aryobimo.uts.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Pref {

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /** menyimpan nilai ke key 'bahasa' berupa String */
    public static void setLanguage(Context context, String language){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString("bahasa", language);
        editor.apply();
    }

    /** Mengembalikan nilai dari key 'bahasa' berupa String
     *
     *  Default value : Indonesia
     * */
    public static String getLanguage(Context context){
        return getSharedPreference(context).getString("bahasa","Indonesia");
    }
}
