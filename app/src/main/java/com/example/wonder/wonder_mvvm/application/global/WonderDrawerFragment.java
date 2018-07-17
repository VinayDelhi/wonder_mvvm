package com.example.wonder.wonder_mvvm.application.global;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.application.di.component.WonderFragmentComponent;
import com.example.wonder.wonder_mvvm.base.WonderFragment;
import com.example.wonder.wonder_mvvm.base.listner.WonderRecyclerItemClickListener;


public class WonderDrawerFragment extends WonderFragment implements WonderRecyclerItemClickListener {
    private TextView userName;
    private TextView parentType;
    private ImageView userImage;
    private LinearLayout viewProfileButton;
    private LinearLayout logOut;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_nav_drawer;
    }

    @Override
    protected void onViewManaged(View view) {
        userName = (TextView) findViewById(R.id.fragment_splash_tv_name);
        parentType = (TextView) findViewById(R.id.fragment_nav_drawer_tv_parent_type);
        userImage = (ImageView) findViewById(R.id.fragment_nav_drawer_iv_image);
        viewProfileButton = (LinearLayout) findViewById(R.id.fragment_nav_drawer_btn_profile);
        logOut = (LinearLayout) findViewById(R.id.fragment_nav_drawer_ll_logout);

        viewProfileButton.setOnClickListener(this);
        logOut.setOnClickListener(this);

        RecyclerView rvDrawer = (RecyclerView) findViewById(R.id.fragment_nav_drawer_rv_item);
        rvDrawer.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void openDrawer() {

    }


    public void setupDrawer(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                openDrawer();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    protected void inject(WonderFragmentComponent injector) {

    }
}
