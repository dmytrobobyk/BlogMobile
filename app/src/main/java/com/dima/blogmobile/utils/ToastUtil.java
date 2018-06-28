package com.dima.blogmobile.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.dima.blogmobile.Injector;


public class ToastUtil {

    public static void makeToast(@StringRes int stringId) {
        Toast.makeText(Injector.getContext(), stringId, Toast.LENGTH_SHORT).show();
    }

    public static void makeToast(String string) {
        Toast.makeText(Injector.getContext(), string, Toast.LENGTH_SHORT).show();
    }
}
