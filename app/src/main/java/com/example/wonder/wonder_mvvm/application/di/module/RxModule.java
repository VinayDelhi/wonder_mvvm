package com.example.wonder.wonder_mvvm.application.di.module;

import com.example.wonder.wonder_mvvm.application.di.rx.AppRxSchedulers;
import com.example.wonder.wonder_mvvm.application.di.rx.RxSchedulers;
import dagger.Module;
import dagger.Provides;
@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
