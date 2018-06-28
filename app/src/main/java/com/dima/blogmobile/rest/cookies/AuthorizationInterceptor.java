package com.dima.blogmobile.rest.cookies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AuthorizationInterceptor implements Interceptor {
    private Map<String, String> headers;

    public AuthorizationInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();

        if(chain.request().url().toString().equals("http://fed-blog.herokuapp.com/api/v1/posts")){
            request = chain.request().newBuilder()
//                    .addHeader("Set-Cookie", headers.get("Set-Cookie"));
                    .addHeader("Cookie", "JSESSIONID=" + headers.get("Set-Cookie").split("=")[1].split(";")[0]);
        }

        return chain.proceed(request.build());
    }
}
