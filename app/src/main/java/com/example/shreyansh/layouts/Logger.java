package com.example.shreyansh.layouts;

import android.util.Log;

public class Logger {
    private static boolean printLog = false;
    private static String TAG = "My Application";

    static public void log(String msg) {
        if (printLog) {
            Log.d(TAG, msg);
        }
    }
}
