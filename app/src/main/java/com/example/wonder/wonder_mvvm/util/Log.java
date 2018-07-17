package com.example.wonder.wonder_mvvm.util;

// This Log class is wrapper around the android default Log Mode and contains all the function required by Log
// Benefit of creating this file is
// 1. to change the Log Level as per development
// 2. add a Object method also which automatically use class name to handle Log Tags
// 3. All the logs have common TAG

/**
 * The type Log.
 */
public class Log {
    private static String LOG_TAG = "Wonder";
    private static final boolean EXTRA_DEBUG = false;

    public static void setLogTag(String logTag) {
        LOG_TAG = logTag;
    }

    /**
     * The constant VERBOSE.
     */
    public static final int VERBOSE = 2;
    /**
     * The constant DEBUG.
     */
    public static final int DEBUG = 3;
    /**
     * The constant INFO.
     */
    public static final int INFO = 4;
    /**
     * The constant WARN.
     */
    public static final int WARN = 5;
    /**
     * The constant ERROR.
     */
    public static final int ERROR = 6;
    /**
     * The constant ASSERT.
     */
    public static final int ASSERT = 7;

    private static final int LOGGING_MODE = VERBOSE;

    /**
     * Is loggable boolean.
     *
     * @param mode the mode
     * @return the boolean
     */
    public static boolean isLoggable(int mode) {
        if (mode >= LOGGING_MODE)
            return true;
        return false;
    }

    /**
     * D int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int d(String tag, String msg) {
        if (isLoggable(DEBUG))
            return android.util.Log.d(LOG_TAG, tag + " : " + msg);
        return 0;
    }

    /**
     * D int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int d(String tag, String msg, Throwable tr) {
        if (isLoggable(DEBUG))
            return android.util.Log.d(LOG_TAG, tag + " : " + msg, tr);
        return 0;
    }

    /**
     * E int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int e(String tag, String msg) {
        if (isLoggable(ERROR))
            return android.util.Log.e(LOG_TAG, tag + " : " + msg);
        return 0;
    }

    /**
     * E int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int e(String tag, String msg, Throwable tr) {
        if (isLoggable(ERROR))
            return android.util.Log.e(LOG_TAG, tag + " : " + msg, tr);
        return 0;
    }

    /**
     * int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int i(String tag, String msg) {
        if (isLoggable(INFO))
            return android.util.Log.i(LOG_TAG, tag + " : " + msg);
        return 0;
    }

    /**
     * int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int i(String tag, String msg, Throwable tr) {
        if (isLoggable(INFO))
            return android.util.Log.i(LOG_TAG, tag + " : " + msg, tr);
        return 0;
    }

    /**
     * V int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int v(String tag, String msg, Throwable tr) {
        if (isLoggable(INFO))
            return android.util.Log.v(LOG_TAG, tag + " : " + msg, tr);
        return 0;
    }

    /**
     * V int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int v(String tag, String msg) {
        if (isLoggable(VERBOSE))
            return android.util.Log.v(LOG_TAG, tag + " : " + msg);
        return 0;
    }

    /**
     * W int.
     *
     * @param tag the tag
     * @param tr  the tr
     * @return the int
     */
    public static int w(String tag, Throwable tr) {
        if (isLoggable(WARN))
            return android.util.Log.w(LOG_TAG, tag + " : ", tr);
        return 0;
    }

    /**
     * W int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int w(String tag, String msg, Throwable tr) {
        if (isLoggable(WARN))
            return android.util.Log.w(LOG_TAG, tag + " : " + msg, tr);
        return 0;
    }

    /**
     * W int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int w(String tag, String msg) {
        if (isLoggable(WARN))
            return android.util.Log.w(LOG_TAG, tag + " : " + msg);
        return 0;
    }


    /**
     * Wtf int.
     *
     * @param tag the tag
     * @param tr  the tr
     * @return the int
     */
    public static int wtf(String tag, Throwable tr) {
        if (isLoggable(ERROR))
            return android.util.Log.wtf(LOG_TAG, tag + " : ", tr);
        return 0;
    }


    /**
     * Wtf int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int wtf(String tag, String msg, Throwable tr) {
        if (isLoggable(ERROR))
            return android.util.Log.wtf(LOG_TAG, tag + " : " + msg, tr);
        return 0;
    }

    /**
     * Wtf int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int wtf(String tag, String msg) {
        if (isLoggable(ERROR))
            return android.util.Log.wtf(LOG_TAG, tag + " : " + msg);
        return 0;
    }

    /**
     * D int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int d(Object tag, String msg) {
        return d(tag.getClass().getSimpleName(), msg);
    }

    /**
     * D int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int d(Object tag, String msg, Throwable tr) {
        return d(tag.getClass().getSimpleName(), msg, tr);
    }

    /**
     * E int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int e(Object tag, String msg) {
        return e(tag.getClass().getSimpleName(), msg);
    }

    /**
     * E int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int e(Object tag, String msg, Throwable tr) {
        return e(tag.getClass().getSimpleName(), msg, tr);
    }

    /**
     * int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int i(Object tag, String msg) {
        return i(tag.getClass().getSimpleName(), msg);
    }

    /**
     * int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int i(Object tag, String msg, Throwable tr) {
        return i(tag.getClass().getSimpleName(), msg, tr);
    }

    /**
     * V int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int v(Object tag, String msg, Throwable tr) {
        return v(tag.getClass().getSimpleName(), msg, tr);
    }

    /**
     * V int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int v(Object tag, String msg) {
        return v(tag.getClass().getSimpleName(), msg);
    }

    /**
     * W int.
     *
     * @param tag the tag
     * @param tr  the tr
     * @return the int
     */
    public static int w(Object tag, Throwable tr) {
        return w(tag.getClass().getSimpleName(), tr);
    }

    /**
     * W int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int w(Object tag, String msg, Throwable tr) {
        return w(tag.getClass().getSimpleName(), msg, tr);
    }

    /**
     * W int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int w(Object tag, String msg) {
        return w(tag.getClass().getSimpleName(), msg);
    }


    /**
     * Wtf int.
     *
     * @param tag the tag
     * @param tr  the tr
     * @return the int
     */
    public static int wtf(Object tag, Throwable tr) {
        return wtf(tag.getClass().getSimpleName(), tr);
    }


    /**
     * Wtf int.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     * @return the int
     */
    public static int wtf(Object tag, String msg, Throwable tr) {
        return wtf(tag.getClass().getSimpleName(), msg, tr);
    }

    /**
     * Wtf int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int wtf(Object tag, String msg) {
        return wtf(tag.getClass().getSimpleName(), msg);
    }

    /**
     * Extra log int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int extraLog(String tag, String msg) {
        if (EXTRA_DEBUG)
            return android.util.Log.v(LOG_TAG, tag + " : " + msg);
        else
            return 0;
    }

    /**
     * Extra log int.
     *
     * @param tag the tag
     * @param msg the msg
     * @return the int
     */
    public static int extraLog(Object tag, String msg) {
        return extraLog(tag.getClass().getSimpleName(), msg);
    }

}
