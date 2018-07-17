package com.example.wonder.wonder_mvvm.view;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.application.global.ActionBarMode;
import com.example.wonder.wonder_mvvm.application.global.WonderDrawerFragment;
import com.example.wonder.wonder_mvvm.application.global.WonderActionBar;
import com.example.wonder.wonder_mvvm.base.WonderActivity;
import com.example.wonder.wonder_mvvm.view.note.NoteFragment;


public class WonderMainActivity extends WonderActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onManageView(Bundle savedInstanceState) {
        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.activity_wonder_main_bar_toolbar);
        setSupportActionBar(toolbar);
        baseActionBar = new WonderActionBar(getSupportActionBar(), toolbar, drawerLayout);
        toolbar.findViewById(R.id.activity_wonder_main_bar_iv_left).setOnClickListener(this);

        // Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        WonderDrawerFragment reachDrawerFragment = (WonderDrawerFragment) getWonderFragmentManager().getFragmentFromId(R.id.activity_wonder_main_fragment_drawer);
        reachDrawerFragment.setupDrawer(drawerLayout);

        getWonderFragmentManager().addFirstFragment(new NoteFragment());
    }

    @Override
    protected int setActivityContent() {
        return R.layout.activity_wonder_main;
    }

    @Override
    public int getFragmentContainer() {
        return R.id.fragment_container;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        //TODO
        if (v.getId() == R.id.activity_wonder_main_bar_iv_left) {
            if (getBaseActionBar().getActionBarMode() == ActionBarMode.MODE_BACK_BUTTON) {
                onBackPressed();
            } else if (getBaseActionBar().getActionBarMode() == ActionBarMode.MODE_CROSS_BUTTON) {
                onBackPressed();
            } else if (getBaseActionBar().getActionBarMode() == ActionBarMode.MODE_HOME_BUTTON) {
            }
        }
    }

}
