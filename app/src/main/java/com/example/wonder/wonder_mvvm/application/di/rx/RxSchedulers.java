package com.example.wonder.wonder_mvvm.application.di.rx;

import io.reactivex.Scheduler;

public interface RxSchedulers {
    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();


}
