package com.dima.blogmobile.rest.cookies;

import android.content.Context;

import com.dima.blogmobile.Injector;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String token = originalResponse.header("Set-Cookie");
//            token = token.substring(token.indexOf('=') + 1, token.indexOf(';'));
            Injector.getLocalStorage().saveToken(token);
        }
        return originalResponse;
    }
}

