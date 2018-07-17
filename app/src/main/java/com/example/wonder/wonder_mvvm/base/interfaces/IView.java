package com.example.wonder.wonder_mvvm.base.interfaces;

import android.arch.lifecycle.LifecycleOwner;

public interface IView extends LifecycleOwner {

    void showLoader(String msg);

    void dismissLoader();

    void showSnackBar(String msg);

    public void showError(Throwable exception);

}