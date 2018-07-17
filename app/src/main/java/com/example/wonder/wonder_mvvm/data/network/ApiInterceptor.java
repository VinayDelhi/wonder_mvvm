package com.example.wonder.wonder_mvvm.data.network;

import com.example.wonder.wonder_mvvm.repository.LocalRepository;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    @Inject
    LocalRepository localRepository;

    @Inject
    ApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .build();


        return chain.proceed(request);
    }
}
