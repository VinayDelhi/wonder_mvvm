package com.example.wonder.wonder_mvvm.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.view.View;

/**
 * <p>
 * Extend this class so that custom actionbar is created
 * Override method like setTitle and setSubtitle to set the title and subtitle
 */

public class WonderBaseActionBar {
    private int actionBarMode = -1;
    private ActionBar actionBar;

    public WonderBaseActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    public int getActionBarMode() {
        return actionBarMode;
    }

    public final void setActionBarMode(int mode) {
        actionBarMode = mode;
        onToolbarModeChanged(mode);
    }

    protected void onToolbarModeChanged(int mode) {
    }

    public void setCustomView(View view) {
        actionBar.setCustomView(view);
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        actionBar.setCustomView(view, layoutParams);
    }

    public void setIcon(@DrawableRes int resId) {
        actionBar.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        actionBar.setIcon(icon);
    }

    public void setLogo(@DrawableRes int resId) {
        actionBar.setLogo(resId);
    }

    public void setLogo(Drawable logo) {
        actionBar.setLogo(logo);
    }

    public void setTitleAndSubtitle(CharSequence title, CharSequence subtitle) {
        actionBar.setTitle(title);
    }

    public void setTitle(CharSequence title) {
        actionBar.setTitle(title);
    }

    public void setSubtitle(CharSequence subtitle) {
        actionBar.setSubtitle(subtitle);
    }

    public void setDisplayOptions(@ActionBar.DisplayOptions int options, @ActionBar.DisplayOptions int mask) {
        actionBar.setDisplayOptions(options, mask);
    }

    public void setDisplayUseLogoEnabled(boolean useLogo) {
        actionBar.setDisplayUseLogoEnabled(useLogo);
    }

    public void setDisplayShowHomeEnabled(boolean showHome) {
        actionBar.setDisplayShowHomeEnabled(showHome);
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    public void setDisplayShowTitleEnabled(boolean showTitle) {
        actionBar.setDisplayShowTitleEnabled(showTitle);
    }

    public void setDisplayShowCustomEnabled(boolean showCustom) {
        actionBar.setDisplayShowCustomEnabled(showCustom);
    }

    public void setActionBarColor(int color) {

    }

    public void setBackgroundDrawable(@Nullable Drawable d) {
        actionBar.setBackgroundDrawable(d);
    }

    public void setStackedBackgroundDrawable(Drawable d) {
        actionBar.setStackedBackgroundDrawable(d);
    }

    public void setSplitBackgroundDrawable(Drawable d) {
        actionBar.setSplitBackgroundDrawable(d);
    }

    public View getCustomView() {
        return actionBar.getCustomView();
    }

    public void setCustomView(int resId) {
        actionBar.setCustomView(resId);
    }

    @Nullable
    public CharSequence getTitle() {
        return actionBar.getTitle();
    }

    public void setTitle(@StringRes int resId) {
        actionBar.setTitle(resId);
    }

    @Nullable
    public CharSequence getSubtitle() {
        return actionBar.getSubtitle();
    }

    public void setSubtitle(int resId) {
        actionBar.setSubtitle(resId);
    }

    @ActionBar.DisplayOptions
    public int getDisplayOptions() {
        return actionBar.getDisplayOptions();
    }

    public void setDisplayOptions(@ActionBar.DisplayOptions int options) {
        actionBar.setDisplayOptions(options);
    }

    public int getHeight() {
        return actionBar.getHeight();
    }

    public void show() {
        actionBar.show();
    }

    public void hide() {
        actionBar.hide();
    }

    public boolean isShowing() {
        return actionBar.isShowing();
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        actionBar.addOnMenuVisibilityListener(listener);
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        actionBar.removeOnMenuVisibilityListener(listener);
    }

    public void setHomeButtonEnabled(boolean enabled) {
        actionBar.setHomeButtonEnabled(enabled);
    }

    public Context getThemedContext() {
        return actionBar.getThemedContext();
    }

    public void setHomeAsUpIndicator(@Nullable Drawable indicator) {
        actionBar.setHomeAsUpIndicator(indicator);
    }

    public void setHomeAsUpIndicator(@DrawableRes int resId) {
        actionBar.setHomeAsUpIndicator(resId);
    }

    public void setHomeActionContentDescription(@Nullable CharSequence description) {
        actionBar.setHomeActionContentDescription(description);
    }

    public void setHomeActionContentDescription(@StringRes int resId) {
        actionBar.setHomeActionContentDescription(resId);
    }

    public boolean isHideOnContentScrollEnabled() {
        return actionBar.isHideOnContentScrollEnabled();
    }

    public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
        actionBar.setHideOnContentScrollEnabled(hideOnContentScroll);
    }

    public int getHideOffset() {
        return actionBar.getHideOffset();
    }

    public void setHideOffset(int offset) {
        actionBar.setHideOffset(offset);
    }

    public float getElevation() {
        return actionBar.getElevation();
    }

    public void setElevation(float elevation) {
        actionBar.setElevation(elevation);
    }


}
