package com.example.wonder.wonder_mvvm.base.recycler;

import android.view.ViewGroup;

import com.example.wonder.wonder_mvvm.base.WonderBaseRecyclerAdapter;

import butterknife.ButterKnife;
public abstract class WonderRecyclerHolder extends WonderBaseRecyclerAdapter.BaseViewHolder {

    protected WonderRecyclerHolder(int layout, ViewGroup parent) {
        super(layout, parent);
        ButterKnife.bind(this, getView());
    }


}
