package com.example.wonder.wonder_mvvm.repository;

import android.content.Context;

import com.example.wonder.wonder_mvvm.data.local.SharedPreferences;
import com.example.wonder.wonder_mvvm.data.local.SharedPreferencesConst;

public class LocalRepository extends SharedPreferences implements SharedPreferencesConst {

    public LocalRepository(Context context) {
        super(context, SHARED_PREFERENCE_NAME);
    }

}