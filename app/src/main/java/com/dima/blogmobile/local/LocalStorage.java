package com.dima.blogmobile.local;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dima.blogmobile.Injector;

import java.util.HashMap;
import java.util.Map;


public class LocalStorage {

    public static final String TOKEN = "token";
    public static final String SET_COOKIE = "Set-Cookie";

    public void saveToken(String token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Injector.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Injector.getContext());
        return preferences.getString(TOKEN, "");
    }

    public Map<String, String> getMapToken() {
        Map<String, String> headers = new HashMap<>();
        String token = getToken();
        if (!token.isEmpty()) {
            headers.put(SET_COOKIE, token);
        }
        return headers;
    }

}
