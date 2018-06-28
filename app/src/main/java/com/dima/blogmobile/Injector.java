package com.dima.blogmobile;

import android.content.Context;

import com.dima.blogmobile.local.LocalStorage;
import com.dima.blogmobile.rest.BlogMobileManager;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Injector {

    static Context context;
    static Scheduler ioScheduler = Schedulers.io();
    static io.reactivex.Scheduler mainScheduler = AndroidSchedulers.mainThread();
    static BlogMobileManager blogMobileManager;
    static LocalStorage localStorage;

    public static Context getContext() {
        return context;
    }

    public static Scheduler getIoScheduler() {
        return ioScheduler;
    }

    public static io.reactivex.Scheduler getMainScheduler() {
        return mainScheduler;
    }

    public static BlogMobileManager getBlogMobileManager() {
        return blogMobileManager;
    }

    public static LocalStorage getLocalStorage() {
        return localStorage;
    }
}
