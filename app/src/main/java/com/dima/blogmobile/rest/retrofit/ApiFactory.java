package com.dima.blogmobile.rest.retrofit;

import android.support.annotation.NonNull;

import com.dima.blogmobile.BuildConfig;
import com.dima.blogmobile.Injector;
import com.dima.blogmobile.rest.cookies.AuthorizationInterceptor;
import com.dima.blogmobile.rest.cookies.ReceivedCookiesInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    @NonNull
    public static BlogMobileService getBlogMobileService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(buildOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(BlogMobileService.class);
    }


    @NonNull
    private static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookiesInterceptor(Injector.getContext()))
                .addInterceptor(new AuthorizationInterceptor(Injector.getLocalStorage().getMapToken()))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
    }
}
