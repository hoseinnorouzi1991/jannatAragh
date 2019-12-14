package com.example.jannataragh.view.base;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {


    private String mCredentials;

    public BasicAuthInterceptor(String username,String password)
    {
        mCredentials = Credentials.basic(username,password);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization",mCredentials)
                .build();

        return chain.proceed(authenticatedRequest);
    }
}
