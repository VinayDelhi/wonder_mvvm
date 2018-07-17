package com.example.wonder.wonder_mvvm.data.network;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall<T> implements Callback<T> {
    private Call<T> call;
    private boolean isCancelable = true;
    private boolean showErrorDialog = false;
    private boolean showErrorSnackBar = true;
    private boolean showLoader = true;
    private String showLoaderText = "Loading";
    private int retryCount = 0;
    private int tag = -1;
    private NetworkCallback<T> networkCallback;
    private Executor executor;

    NetworkCall(Call<T> call) {
        this(call, new UiThreadExecutor());
    }

    NetworkCall(Call<T> call, Executor executor) {
        this.call = call;
        this.executor = executor;
    }


    public Call<T> getCall() {
        return call;
    }


    public boolean isShowErrorDialog() {
        return showErrorDialog;
    }

    public NetworkCall setShowErrorDialog(boolean showErrorDialog) {
        checkEditable();
        this.showErrorDialog = showErrorDialog;
        return this;
    }

    public boolean isShowErrorSnackBar() {
        return showErrorSnackBar;
    }

    public NetworkCall setShowErrorSnackBar(boolean showErrorSnackBar) {
        checkEditable();
        this.showErrorSnackBar = showErrorSnackBar;
        return this;
    }

    public boolean isShowLoader() {
        return showLoader;
    }

    public NetworkCall setShowLoader(boolean showLoader) {
        this.showLoader = showLoader;
        return this;
    }

    public String getShowLoaderText() {
        return showLoaderText;
    }

    public NetworkCall setShowLoaderText(String showLoaderText) {
        checkEditable();
        showLoader = true;
        this.showLoaderText = showLoaderText;
        return this;
    }


    public boolean isCancelable() {
        return isCancelable;
    }

    public NetworkCall<T> setCancelable(boolean cancelable) {
        checkEditable();
        isCancelable = cancelable;
        return this;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(int tag) {
        checkEditable();
        this.tag = tag;
    }


    public Integer getRetryCount() {
        return retryCount;
    }

    public NetworkCall<T> setRetryCount(int retryCount) {
        checkEditable();
        this.retryCount = retryCount;
        return this;
    }

    private void checkEditable() {
        if (call.isExecuted() || call.isCanceled()) {
            throw new IllegalStateException("Call can't be changed if it is already executed or cancelled");
        }
    }

    //implemented Retrofit Call Method

    public Response<T> execute() throws IOException {
        return call.execute();
    }

    public void enqueue(NetworkCallback<T> callback) {
        this.networkCallback = callback;
        call.enqueue(this);
    }

    public boolean isExecuted() {
        return call.isExecuted();
    }

    public void cancel() {
        call.cancel();
    }

    public boolean isCanceled() {
        return call.isCanceled();
    }

    public NetworkCall<T> clone() {
        return new NetworkCall<T>(call.clone());
    }

    public Request request() {
        return call.request();
    }

    //implemented Retrofit Call Method


    @Override
    public void onResponse(Call<T> call, final Response<T> response) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                networkCallback.onResponse(NetworkCall.this, response);
            }
        });
    }

    @Override
    public void onFailure(Call<T> call, final Throwable t) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                networkCallback.onFailure(NetworkCall.this, t);
            }
        });
    }

    private static class UiThreadExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
        }
    }

}