package com.example.wonder.wonder_mvvm.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wonder.wonder_mvvm.application.di.component.ApplicationComponent;
import com.example.wonder.wonder_mvvm.application.di.component.DaggerWonderFragmentComponent;
import com.example.wonder.wonder_mvvm.application.di.component.WonderFragmentComponent;
import com.example.wonder.wonder_mvvm.application.global.WonderMainApplication;
import com.example.wonder.wonder_mvvm.base.interfaces.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class WonderFragment extends WonderBaseFragment implements IView {
    private WonderFragmentComponent injector;
    private ProgressDialog progressDialog;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent applicationComponent = WonderMainApplication.getApplicationComponent();
        injector = DaggerWonderFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        inject(injector);
    }

    protected abstract void inject(WonderFragmentComponent injector);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        // TODO
        ButterKnife.setDebug(true);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showLoader(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void dismissLoader() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showError(Throwable exception) {
        showSnackBar(exception.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissLoader();
        if (unbinder != null)
            unbinder.unbind();
    }

}