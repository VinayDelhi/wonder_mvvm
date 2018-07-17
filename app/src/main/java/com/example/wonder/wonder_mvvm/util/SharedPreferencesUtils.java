package com.example.wonder.wonder_mvvm.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

// A proxy Object for Shared Preferences
// Use case is no need to create SharedPreferences always, initialise it once on APP starts
// encryption policy can be applied if required

public class SharedPreferencesUtils {
    public static final Integer DEFAULT_INTEGER = -1;
    public static final Long DEFAULT_LONG = 0L;
    public static final Float DEFAULT_FLOAT = 0f;
    public static final Boolean DEFAULT_BOOLEAN = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public SharedPreferencesUtils(Context context, String sharedPrefName) {
        sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    protected Integer getInt(String key) {
        return getInt(key, DEFAULT_INTEGER);
    }

    protected Integer getInt(String key, Integer defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    protected void putString(String key, String value) {
        if (value == null) {
            removeKey(key);
        } else {
            editor.putString(key, value);
            editor.apply();
        }
    }

    protected String getString(String key) {
        return getString(key, null);
    }

    protected String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    protected void putLong(String key, Long value) {
        if (value == null) {
            removeKey(key);
        } else {
            editor.putLong(key, value);
            editor.apply();
        }
    }

    protected Long getLong(String key) {
        return getLong(key, DEFAULT_LONG);
    }

    protected Long getLong(String key, Long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    protected void putDouble(String key, Double value) {
        if (value == null) {
            removeKey(key);
        } else {
            editor.putLong(key, Double.doubleToRawLongBits(value));
            editor.apply();
        }
    }

    protected Double getDouble(String key) {
        return getDouble(key, null);
    }

    protected Double getDouble(String key, Double defValue) {
        return Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToRawLongBits(defValue)));
    }

    protected void putFloat(String key, Float value) {
        if (value == null) {
            removeKey(key);
        } else {
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    protected Float getFloat(String key) {
        return getFloat(key, DEFAULT_FLOAT);
    }

    protected Float getFloat(String key, Float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    protected void putBoolean(String key, Boolean value) {
        if (value == null) {
            removeKey(key);
        } else {
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    protected Boolean getBoolean(String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    protected Boolean getBoolean(String key, Boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    protected void putObject(String key, Object value) {
        if (value == null) {
            removeKey(key);
        } else {
            String str = gson.toJson(value);
            editor.putString(key, str);
            editor.apply();
        }
    }

    protected <T> T getObject(String key, Class<T> tClass) {
        return getObject(key, tClass, null);
    }

    protected <T> T getObject(String key, Class<T> tClass, T defValue) {
        if (contains(key)) {
            String str = getString(key);
            if (str != null) {
                T t = gson.fromJson(str, tClass);
                return t;
            }
        }
        return defValue;
    }

    protected <T> T getObject(String key, Type tClass, T defValue) {
        if (contains(key)) {
            String str = getString(key);
            if (str != null) {
                T t = gson.fromJson(str, tClass);
                return t;
            }
        }
        return defValue;
    }

    protected boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    protected void removeKey(String key) {
        editor.remove(key);
    }

    protected void putInt(String key, Integer value) {
        if (value == null) {
            removeKey(key);
        } else {
            editor.putInt(key, value);
            editor.apply();
        }
    }

    protected void clearAll() {
        editor.clear();
        editor.apply();
    }

}
