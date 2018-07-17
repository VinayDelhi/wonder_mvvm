package com.example.wonder.wonder_mvvm.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wonder.wonder_mvvm.base.listner.WonderRecyclerItemClickListener;

public abstract class WonderBaseRecyclerAdapter<T extends WonderBaseRecyclerAdapter.BaseViewHolder> extends RecyclerView.Adapter<T> {

    private WonderRecyclerItemClickListener recyclerItemClickListener;

    public void setRecyclerItemClickListener(WonderRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public final T onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent.getContext(), parent, viewType);
    }

    public abstract T onCreateViewHolder(Context context, ViewGroup parent, int itemLayout);

    @Override
    public final void onBindViewHolder(T holder, int position) {
        holder.setRecyclerItemClickListener(recyclerItemClickListener);
        onBindVH(holder, position);
    }

    public abstract void onBindVH(T holder, int position);

    @Override
    public final int getItemCount() {
        return getCount();
    }

    protected abstract int getCount();


    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View itemView;
        protected WonderRecyclerItemClickListener recyclerItemClickListener;

        protected BaseViewHolder(int layout, ViewGroup parent) {
            this(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        }

        private BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnClickListener(this);
        }

        public void setRecyclerItemClickListener(WonderRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public View getView() {
            return itemView;
        }

        public View findViewById(int id) {
            return itemView.findViewById(id);
        }

        @Override
        public void onClick(View v) {
            if (recyclerItemClickListener != null) {
                recyclerItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

}
