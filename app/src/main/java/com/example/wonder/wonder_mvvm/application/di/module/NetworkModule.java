package com.example.wonder.wonder_mvvm.application.di.module;

import android.content.Context;

import com.example.wonder.wonder_mvvm.application.di.rx.AppRxSchedulers;
import com.example.wonder.wonder_mvvm.application.di.scope.ApplicationScope;
import com.example.wonder.wonder_mvvm.data.network.ApiConverter;
import com.example.wonder.wonder_mvvm.data.network.ApiInterceptor;
import com.example.wonder.wonder_mvvm.data.network.ApiServices;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final String DEV_URL = "https://swapi.co/api/";

    private static final String BASE_URL = DEV_URL;

    @ApplicationScope
    @Provides
    ApiServices provideApiService(OkHttpClient client, GsonConverterFactory gson,
                                  CallAdapter.Factory rxAdapter, ApiConverter apiConverter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addConverterFactory(apiConverter)
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(ApiServices.class);
    }


    @ApplicationScope
    @Provides

    OkHttpClient provideHttpClient(HttpLoggingInterceptor logger, ApiInterceptor apiInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logger);
        builder.addInterceptor(apiInterceptor);
        /*builder.cache(cache);*/
        return builder.build();
    }

    @ApplicationScope
    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


    @ApplicationScope
    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @ApplicationScope
    @Provides
    CallAdapter.Factory provideRxAdapter() {
        return RxJava2CallAdapterFactory.createWithScheduler(AppRxSchedulers.INTERNET_SCHEDULERS);
    }

    @Provides
    @ApplicationScope
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }

}

