package com.example.wonder.wonder_mvvm.base;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.base.view.CircularProgressDialog;
import com.example.wonder.wonder_mvvm.data.network.NetworkCall;
import com.example.wonder.wonder_mvvm.data.network.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;

public abstract class WonderBaseFragment extends DialogFragment implements View.OnClickListener, WonderBaseActivity.OnBackPressedListener, NetworkCallback {

    private Map<NetworkCall, NetworkCallback> networkCallsMap;
    private int title;
    private WonderFragmentManager mChildFragmentManager;
    private CircularProgressDialog circularProgressDialog;
    private boolean showErrorSnackBar = true;
    private boolean showLoader = true;

    public void setShowLoader(boolean showLoader) {
        this.showLoader = showLoader;
    }

    public void setShowErrorSnackBar(boolean showErrorSnackBar) {
        this.showErrorSnackBar = showErrorSnackBar;
    }

    @Nullable
    public final View findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }

    abstract protected int fragmentLayout();

    abstract protected void onViewManaged(View view);

    @Deprecated
    protected boolean hasNetworkCall() {
        return false;
    }

    @Nullable
    @Override
    final public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(fragmentLayout(), container, false);
        return view;
    }

    public WonderBaseActivity getBaseActivity() {
        return (WonderBaseActivity) getActivity();
    }

    public WonderBaseActionBar getBaseActionBar() {
        if (getBaseActivity() == null) return null;
        return getBaseActivity().getBaseActionBar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = new WonderFragmentManager(getChildFragmentManager());
        //setRetainInstance(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int transition = R.transition.view_transition;

            setSharedElementEnterTransition(TransitionInflater.from(getActivity())
                    .inflateTransition(transition));
            setSharedElementReturnTransition(TransitionInflater.from(getActivity())
                    .inflateTransition(transition));
            setEnterTransition(TransitionInflater.from(getActivity())
                    .inflateTransition(transition));
            setEnterTransition(TransitionInflater.from(getActivity())
                    .inflateTransition(transition));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        onViewManaged(view);
        if (getBaseActivity() != null) {
            if (hasNetworkCall())
                makeNetworkCall();
            if (getArguments() != null)
                loadData(getArguments());
            if (getBaseActionBar() != null)
                setActionBar(getBaseActionBar());
        }
    }

    @Override
    public void onClick(View v) {
    }

    protected void loadData(Bundle bundle) {
    }

    protected void setActionBar(WonderBaseActionBar actionBar) {
    }

    public WonderFragmentManager getWonderFragmentManager() {
        if (getBaseActivity() != null)
            return getBaseActivity().getWonderFragmentManager();
        return null;
    }

    public WonderFragmentManager getWonderChildFragmentManager() {
        if (mChildFragmentManager == null) {
            mChildFragmentManager = new WonderFragmentManager(getChildFragmentManager());
        }
        return mChildFragmentManager;
    }

    public WonderBaseFragment getWonderParentFragment() {
        return (WonderBaseFragment) getParentFragment();
    }

    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int result = getBaseActivity().checkSelfPermission(permission);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public boolean requestPermission(String permission, int requestCode, String message) {
        return requestPermission(new String[]{permission}, requestCode, message);
    }

    public boolean requestPermission(String[] permission, int requestCode, String message) {
        boolean displayPermissionDialog = true;
        for (String per : permission) {
            if (shouldShowRequestPermissionRationale(per)) {
                displayPermissionDialog = false;
                break;
            }
        }
        if (!displayPermissionDialog) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }

        requestPermissions(permission, requestCode);
        return displayPermissionDialog;
    }


    public void showSnackBar(String message) {
        showSnackBar(message, null, null);
    }

    protected void showSnackBar(String message, int length) {
        showSnackBar(message, length, null, null);
    }

    protected void showSnackBar(String message, String buttonStr, View.OnClickListener listener) {
        showSnackBar(message, Snackbar.LENGTH_SHORT, buttonStr, listener);
    }


    protected void showSnackBar(String message, int length, String buttonStr, View.OnClickListener listener) {
        if (getBaseActivity() == null)
            return;
        Snackbar snackbar = Snackbar.make(getBaseActivity().findViewById(getBaseActivity().getFragmentContainer()),
                message, length);
        snackbar.setAction(buttonStr, listener);

        int textColor = getContext().getResources().getColor(android.R.color.white);
        TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(textColor);
        snackbar.setActionTextColor(textColor);
        snackbar.show();
    }

    @Deprecated
    protected void makeNetworkCall() {
    }

    public int getCode() {
        return title;
    }

    public void setCode(int title) {
        this.title = title;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    public void handleBack(boolean handleBack) {
        if (handleBack && getBaseActivity() != null) {
            getBaseActivity().setOnBackPressedListener(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getBaseActivity().removeOnBackPressedListener(this);
        cancelAllCalls();
    }

    public void showLoader(String msg) {
        if (circularProgressDialog == null)
            circularProgressDialog = new CircularProgressDialog(getContext());
        circularProgressDialog.display(msg);
    }

    public void dismissLoader() {
        if (circularProgressDialog != null) {
            circularProgressDialog.dismiss();
            circularProgressDialog = null;
        }
    }

    //Network Calls
    public final <T> void makeCall(NetworkCall<T> networkCall, NetworkCallback<T> callback) {
        if (networkCallsMap == null) networkCallsMap = new HashMap<>();
        networkCallsMap.put(networkCall, callback);
        networkCall.enqueue(this);

        if (showLoader && networkCall.isShowLoader()) {
            showLoader(networkCall.getShowLoaderText());
        }
    }

    protected final void removeCall(NetworkCall call) {
        if (networkCallsMap == null) return;
        networkCallsMap.remove(call);
        boolean display = false;

        for (NetworkCall networkCall : networkCallsMap.keySet()) {
            if (networkCall.isShowLoader()) {
                display = true;
                break;
            }
        }
        if (!display) dismissLoader();
    }

    protected final void cancelAllCalls() {
        if (networkCallsMap == null) return;

        for (NetworkCall call : networkCallsMap.keySet()) {
            if (call.isCancelable()) call.cancel();
        }
    }

    @Override
    public final String onResponse(final NetworkCall call, final Response response) {
        if (getBaseActivity() != null && getBaseActivity().onResponse(call, response) != null) {
            removeCall(call);
            return null;
        }

        NetworkCallback networkCallBack = networkCallsMap.get(call);
        if (networkCallBack != null) {
            String errorMsg = networkCallBack.onResponse(call, response);
            if (errorMsg != null && showErrorSnackBar && call.isShowErrorSnackBar()) {
                showSnackBar(errorMsg);
            }
        }
        removeCall(call);
        return null;
    }

    @Override
    public final String onFailure(final NetworkCall call, final Throwable t) {
        if (getBaseActivity() != null && getBaseActivity().onFailure(call, t) != null) {
            removeCall(call);
            return null;
        }
        NetworkCallback networkCallBack = networkCallsMap.get(call);
        if (networkCallBack != null) {
            String errorMsg = networkCallBack.onFailure(call, t);
            if (errorMsg != null && showErrorSnackBar && call.isShowErrorSnackBar() && !call.isCanceled()) {
                showSnackBar(errorMsg);
            }
        }
        removeCall(call);
        return null;
    }

//Network Calls End
}

