package com.example.wonder.wonder_mvvm.application.di.component;

import com.example.wonder.wonder_mvvm.application.di.module.ApplicationModule;
import com.example.wonder.wonder_mvvm.application.di.module.NetworkModule;
import com.example.wonder.wonder_mvvm.application.di.module.RxModule;
import com.example.wonder.wonder_mvvm.application.di.rx.RxSchedulers;
import com.example.wonder.wonder_mvvm.application.di.scope.ApplicationScope;
import com.example.wonder.wonder_mvvm.data.network.ApiServices;
import com.example.wonder.wonder_mvvm.repository.LocalRepository;

import dagger.Component;


@ApplicationScope
@Component(modules = {NetworkModule.class, ApplicationModule.class, RxModule.class})
public interface ApplicationComponent {

    RxSchedulers rxSchedulers();

    ApiServices apiService();

    LocalRepository localRepository();

}
