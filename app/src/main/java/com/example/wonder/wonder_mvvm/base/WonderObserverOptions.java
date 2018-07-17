package com.example.wonder.wonder_mvvm.base;

public class WonderObserverOptions {
    private boolean isCancelable = true;
    private boolean showErrorSnackBar = true;
    //private boolean showLoader = true;
    private boolean showLoader = false;
    private String showLoaderText = "Loading";

    public WonderObserverOptions() {
    }

    public WonderObserverOptions(boolean isCancelable, boolean showLoader, String showLoaderText) {
        this.isCancelable = isCancelable;
        this.showLoader = showLoader;
        this.showLoaderText = showLoaderText;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public void setCancelable(boolean cancelable) {
        isCancelable = cancelable;
    }

    public boolean isShowErrorSnackBar() {
        return showErrorSnackBar;
    }

    public void setShowErrorSnackBar(boolean showErrorSnackBar) {
        this.showErrorSnackBar = showErrorSnackBar;
    }

    public boolean isShowLoader() {
        return showLoader;
    }

    public void setShowLoader(boolean showLoader) {
        this.showLoader = showLoader;
    }

    public String getShowLoaderText() {
        return showLoaderText;
    }

    public void setShowLoaderText(String showLoaderText) {
        this.showLoaderText = showLoaderText;
    }

}