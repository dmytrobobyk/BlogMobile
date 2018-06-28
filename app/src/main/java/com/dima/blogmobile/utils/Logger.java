package com.dima.blogmobile.utils;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by dima on 15.12.17.
 */

public class Logger {

    public static String getClassTag(@NonNull Class<?> someClass) {
        return someClass.getName();
    }

    public static void e(String tag, Exception e) {
        Log.e(tag, e.toString());
    }

    public static void t(String tag, Throwable t) {
        Log.e(tag, t.toString());
    }

    public static void d(String tag, String message) {
        Log.d(tag, message);
    }
}
