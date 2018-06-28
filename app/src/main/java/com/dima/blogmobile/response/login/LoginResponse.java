package com.dima.blogmobile.response.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("Set-Cookie")
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
