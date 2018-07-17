package com.example.wonder.wonder_mvvm.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.wonder.wonder_mvvm.base.interfaces.IView;

import javax.inject.Inject;

public abstract class BaseViewModel<T extends IView> implements LifecycleObserver {

    @Inject
    protected WonderObserverHandler handler;

    protected T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
        this.handler.setView(view);
        this.view.getLifecycle().addObserver(this);
    }

    protected Lifecycle.State getCurrentState() {
        return this.view.getLifecycle().getCurrentState();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        handler.cancelAllCalls();
    }

}
