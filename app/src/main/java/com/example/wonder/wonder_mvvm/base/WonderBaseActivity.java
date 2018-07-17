package com.example.wonder.wonder_mvvm.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.data.network.NetworkCall;
import com.example.wonder.wonder_mvvm.data.network.NetworkCallback;

import java.lang.ref.WeakReference;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class WonderBaseActivity extends AppCompatActivity implements View.OnClickListener, NetworkCallback {

    protected WeakReference<OnBackPressedListener> onBackPressedListener;
    protected WonderBaseActionBar baseActionBar;
    private WonderFragmentManager mFragmentManager;

    protected abstract void onManageView(Bundle savedInstanceState);

    protected abstract int setActivityContent();

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = new WonderFragmentManager(getSupportFragmentManager(), getFragmentContainer());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        setContentView(setActivityContent());
        onManageView(savedInstanceState);
    }

    public WonderBaseActionBar getBaseActionBar() {
        if (baseActionBar == null && getSupportActionBar() != null) {
            baseActionBar = new WonderBaseActionBar(getSupportActionBar());
        }
        return baseActionBar;
    }

    public abstract int getFragmentContainer();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentManager = null;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
    }

    public WonderFragmentManager getWonderFragmentManager() {
        return mFragmentManager;
    }

    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int result = ContextCompat.checkSelfPermission(this, permission);
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, per)) {
                displayPermissionDialog = false;
                break;
            }
        }
        if (!displayPermissionDialog) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, permission, requestCode);
        }
        return displayPermissionDialog;
    }

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        this.onBackPressedListener = new WeakReference<>(listener);
    }

    public void removeOnBackPressedListener(OnBackPressedListener listener) {
        if (onBackPressedListener != null && onBackPressedListener.get() == listener) {
            this.onBackPressedListener = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null && onBackPressedListener.get() != null) {
            if (!onBackPressedListener.get().onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public String onResponse(NetworkCall call, Response response) {
        return null;
    }

    @Override
    public String onFailure(NetworkCall call, Throwable t) {
        return null;
    }

    public interface OnBackPressedListener {
        boolean onBackPressed();
    }
}
