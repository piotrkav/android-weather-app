package com.example.piotr.pl5_task.consts;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String HELPER_NAME = "weather_preferences";
    private static final String PREF_CITY = "pref_city";
    private static SharedPreferences sharedPreferences;
    private static PreferencesHelper singletonPreferencesHelper = null;

    private PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(HELPER_NAME, Context.MODE_PRIVATE);

    }

    public static synchronized PreferencesHelper getInstance(Context context) {
        if (singletonPreferencesHelper == null) {
            synchronized (PreferencesHelper.class) {
                if (singletonPreferencesHelper == null) {
                    singletonPreferencesHelper = new PreferencesHelper(context);
                }
            }
        }
        return singletonPreferencesHelper;
    }


    public void addPreferedCity(String city) {
        sharedPreferences.edit().putString(PREF_CITY, city).apply();
    }

    public String getPrefCity() {
        return sharedPreferences.getString(PREF_CITY, "");
    }
}
