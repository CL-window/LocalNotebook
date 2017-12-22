package com.cl.slack.studentnotbook.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.lang.ref.WeakReference;

public abstract class BaseAdapter<Holder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<Holder> {

    private final WeakReference<Context> mContext;
    protected final WeakReference<RecyclerView> mRecyclerView;
    private final WeakReference<LayoutInflater> mLayoutInflater;

    protected final Handler mMainHandler;

    public BaseAdapter(@NonNull RecyclerView recyclerView) {
        mRecyclerView = new WeakReference<>(recyclerView);
        Context context = recyclerView.getContext();
        mContext = new WeakReference<>(context);
        mLayoutInflater = new WeakReference<>(LayoutInflater.from(context));
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    protected RecyclerView.Adapter getCurrentAdapter() {
        RecyclerView recyclerView = mRecyclerView.get();
        if (recyclerView != null) {
            return recyclerView.getAdapter();
        }
        return null;
    }

    protected Holder getViewHolder(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }

        RecyclerView recyclerView = mRecyclerView.get();
        if (recyclerView != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null && adapter.equals(this)){
                RecyclerView.ViewHolder vh = recyclerView.findViewHolderForAdapterPosition(i);
                if (vh != null) {
                    return (Holder) vh;
                }
            }
        }
        return null;
    }

    protected Context getContext() {
        return mContext.get();
    }

    protected View inflateView(@LayoutRes int id, ViewGroup root, boolean attach) {
        LayoutInflater inflater = mLayoutInflater.get();
        if (inflater != null) {
            return inflater.inflate(id, root, attach);
        }
        return null;
    }

    protected int getColor(@ColorRes int id){
        Context context = getContext();
        if (context == null) {
            return android.R.color.transparent;
        }
        return context.getResources().getColor(id);
    }

    protected String getString(@StringRes int id){
        Context context = getContext();
        if (context == null) {
            return "";
        }

        return context.getString(id);
    }

    protected void runOnUiThread(Runnable runnable) {
        mMainHandler.post(runnable);
    }

    protected void showCenterToast(String msg) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void release() {
        mContext.clear();
        mRecyclerView.clear();
        mLayoutInflater.clear();
    }
}