package com.example.apteka.pref_data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceData {

    public String PREFERENCE_NAME = "my_preference";
    public Context context;
    public SharedPreferences preferences;

    public PreferenceData(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getData(String key) {
        return preferences.getString(key, "");
    }

    public void clearData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


}
