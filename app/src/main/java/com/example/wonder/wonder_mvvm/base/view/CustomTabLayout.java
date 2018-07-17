package com.example.wonder.wonder_mvvm.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;

public class CustomTabLayout extends TabLayout {
    private static final int NONE = -2;
    private Tab oldTab;
    private int textColor;
    private int selectedTextColor;
    private float textSize;
    private float selectedTextSize;
    private String fontFile;
    private String selectedFontFile;
    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Tab tab = getTabAt(position);
            changeTab(oldTab, tab);
            oldTab = tab;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private OnTabSelectedListener tabSelectedListener = new OnTabSelectedListener() {
        @Override
        public void onTabSelected(Tab tab) {
            changeTab(oldTab, tab);
            oldTab = tab;
        }

        @Override
        public void onTabUnselected(Tab tab) {
        }

        @Override
        public void onTabReselected(Tab tab) {
        }
    };

    public CustomTabLayout(Context context) {
        this(context, null);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout);
        textColor = attributes.getColor(R.styleable.CustomTabLayout_tab_text_color, NONE);
        selectedTextColor = attributes.getColor(R.styleable.CustomTabLayout_select_text_color, NONE);
        textSize = attributes.getDimension(R.styleable.CustomTabLayout_text_size, NONE);
        selectedTextSize = attributes.getDimension(R.styleable.CustomTabLayout_text_size, NONE);
        fontFile = attributes.getString(R.styleable.CustomTabLayout_font_file);
        selectedFontFile = attributes.getString(R.styleable.CustomTabLayout_select_font_file);
        attributes.recycle();

        init();
    }

    void init() {
        setOnTabSelectedListener(tabSelectedListener);
    }

    @Override
    public void addTab(@NonNull Tab tab, boolean setSelected) {
        tab.setCustomView(R.layout.view_custom_tab);
        changeTab(tab, null);
        super.addTab(tab, setSelected);
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(viewPagerListener);
            int currentItem = viewPager.getCurrentItem();
            Tab tabAt = getTabAt(currentItem);
            changeTab(oldTab, tabAt);
            oldTab = tabAt;
        }
    }

    public void changeTab(Tab oldTab, Tab newTab) {
        if (oldTab != null && oldTab.getCustomView() != null) {
            TextView v = (TextView) oldTab.getCustomView().findViewById(android.R.id.text1);
            if (textSize != NONE)
                v.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            if (textColor != NONE)
                v.setTextColor(textColor);
            if (fontFile != null) {
                v.setTypeface(Typeface.createFromAsset(getContext().getAssets(), fontFile));
            }
        }

        if (newTab != null && newTab.getCustomView() != null) {
            TextView v = (TextView) newTab.getCustomView().findViewById(android.R.id.text1);
            if (selectedTextSize != NONE)
                v.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTextSize);
            if (selectedTextColor != NONE)
                v.setTextColor(selectedTextColor);
            if (selectedFontFile != null) {
                v.setTypeface(Typeface.createFromAsset(getContext().getAssets(), selectedFontFile));
            }
        }
        invalidate();
    }

}