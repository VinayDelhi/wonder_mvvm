package com.example.wonder.wonder_mvvm.application.global;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.multidex.MultiDex;
import android.util.Base64;

import com.example.wonder.wonder_mvvm.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WonderGlobalClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.setLogTag(getPackageName());
        MultiDex.install(getBaseContext());
        printHashKey();
    }

    public void printHashKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(WonderGlobalClass.class.getName(), "KeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
    }

}



