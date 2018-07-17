package com.example.wonder.wonder_mvvm.base;

import io.reactivex.observers.DefaultObserver;

public abstract class WonderObserver<T> extends DefaultObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public void unsubscribe() {
        super.cancel();
    }


}