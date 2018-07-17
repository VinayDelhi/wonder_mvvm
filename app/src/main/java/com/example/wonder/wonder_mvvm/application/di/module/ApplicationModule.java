package com.example.wonder.wonder_mvvm.application.di.module;

import android.content.Context;

import com.example.wonder.wonder_mvvm.application.di.scope.ApplicationScope;
import com.example.wonder.wonder_mvvm.repository.LocalRepository;

import dagger.Module;
import dagger.Provides;
@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context aContext) {
        this.context = aContext;
    }

    @ApplicationScope
    @Provides
    Context provideAppContext() {
        return context;
    }

    @ApplicationScope
    @Provides
    LocalRepository provideLocalRepository() {
        return new LocalRepository(context);
    }

}
