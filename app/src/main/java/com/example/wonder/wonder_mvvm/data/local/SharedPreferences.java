package com.example.wonder.wonder_mvvm.data.local;

import android.content.Context;

import com.example.wonder.wonder_mvvm.util.SharedPreferencesUtils;

public abstract class SharedPreferences extends SharedPreferencesUtils {
    public SharedPreferences(Context context, String sharedPrefName) {
        super(context, sharedPrefName);
    }
}
