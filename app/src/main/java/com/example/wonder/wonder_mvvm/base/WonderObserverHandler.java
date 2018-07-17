package com.example.wonder.wonder_mvvm.base;

import com.example.wonder.wonder_mvvm.base.interfaces.IView;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WonderObserverHandler {
    private Map<WonderObserver, WonderObserverOptions> observerMap = new HashMap<>();
    private WeakReference<IView> iView;

    @Inject
    public WonderObserverHandler() {
    }

    public void setView(IView iView) {
        this.iView = new WeakReference<>(iView);
    }

    public final <T> void subscribe(Observable<T> observable, WonderObserver<T> reachObserver) {
        subscribe(observable, reachObserver, new WonderObserverOptions());
    }

    public final <T> void subscribe(Observable<T> observable, WonderObserver<T> reachObserver, WonderObserverOptions data) {
        observerMap.put(reachObserver, data);

        observable
                .doOnNext(t -> {
                    onResponse(reachObserver, t);
                })
                .doOnError(throwable -> {
                    onFailure(reachObserver, throwable);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reachObserver);

        if (data.isShowLoader()) {
            if (iView != null && iView.get() != null)
                iView.get().showLoader(data.getShowLoaderText());
        }
    }

    protected void unsubscribe(WonderObserver observer) {
        observer.unsubscribe();
        removeObserverAndUpdate(observer);
    }

    public void cancelAllCalls() {
        for (WonderObserver observer : observerMap.keySet()) {
            if (observerMap.get(observer).isCancelable())
                observer.unsubscribe();
        }
    }


    private void onResponse(final WonderObserver observer, final Object object) {
        removeObserverAndUpdate(observer);
    }

    private void onFailure(final WonderObserver observer, final Throwable t) {
        removeObserverAndUpdate(observer);
    }

    void removeObserverAndUpdate(final WonderObserver observer) {
        observerMap.remove(observer);
        boolean displayLoader = false;

        for (WonderObserver reachObserver : observerMap.keySet()) {
            if (observerMap.get(reachObserver).isShowLoader()) {
                displayLoader = true;
                break;
            }
        }
        if (!displayLoader) {
            if (iView != null && iView.get() != null)
                iView.get().dismissLoader();
        }
    }

}
