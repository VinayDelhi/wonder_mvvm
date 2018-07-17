package com.example.wonder.wonder_mvvm.application.global;

import com.example.wonder.wonder_mvvm.application.di.component.ApplicationComponent;
import com.example.wonder.wonder_mvvm.application.di.component.DaggerApplicationComponent;
import com.example.wonder.wonder_mvvm.application.di.module.ApplicationModule;


public class WonderMainApplication extends WonderApplication {
    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

}