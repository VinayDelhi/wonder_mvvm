package com.example.wonder.wonder_mvvm.application.global;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.base.WonderBaseActionBar;

public class WonderActionBar extends WonderBaseActionBar {
    private TextView tvTitle;
    private TextView tvSubtitle;
    private Toolbar toolbar;
    private ImageView ivIcon;
    private DrawerLayout drawerLayout;

    public WonderActionBar(ActionBar actionBar, Toolbar toolbar, DrawerLayout drawerLayout) {
        super(actionBar);
        this.toolbar = toolbar;
        this.drawerLayout = drawerLayout;
        setDisplayShowTitleEnabled(false);
        tvTitle = toolbar.findViewById(R.id.activity_wonder_main_bar_tv_title);
        ivIcon = toolbar.findViewById(R.id.activity_wonder_main_bar_iv_left);
        tvSubtitle = toolbar.findViewById(R.id.activity_wonder_main_bar_tv_subtitle);
    }

    public void setTitleAndSubtitle(CharSequence title, CharSequence subtitle) {
        setTitle(title);
        setSubtitle(subtitle);
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setSubtitle(null);
        tvTitle.setText(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        setSubtitle(null);
        tvTitle.setText(title);
    }

    @Override
    public void setSubtitle(int resId) {
        tvSubtitle.setText(resId);
    }

    @Override
    public void setSubtitle(CharSequence subtitle) {
        tvSubtitle.setVisibility(subtitle != null ? View.VISIBLE : View.GONE);
        tvSubtitle.setText(subtitle);
    }

    public void setActionBarColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    @Override
    public void setHomeAsUpIndicator(@DrawableRes int resId) {
        super.setHomeAsUpIndicator(resId);
    }

    @Override
    protected void onToolbarModeChanged(int mode) {
        switch (mode) {
            /*case ActionBarMode.MODE_HOME_BUTTON:
                ivIcon.setImageResource(R.drawable.ic_hamburger);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;*/
            case ActionBarMode.MODE_BACK_BUTTON:
                ivIcon.setImageResource(R.drawable.ic_back);
                ivIcon.setVisibility(View.VISIBLE);
                break;
            case ActionBarMode.MODE_CROSS_BUTTON:
                ivIcon.setImageResource(R.drawable.ic_cross);
                ivIcon.setVisibility(View.VISIBLE);
                break;
            default:
                ivIcon.setVisibility(View.INVISIBLE);
        }
    }
}
