package com.cl.slack.studentnotbook.widget;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public View findView(@IdRes int id) {
        return itemView.findViewById(id);
    }

    public int getColor(@ColorRes int id) {
        Context context = itemView.getContext();
        return context.getResources().getColor(id);
    }

    public String getString(@StringRes int id) {
        Context context = itemView.getContext();
        return context.getString(id);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
    }

    public void setOnTouchListener(View.OnTouchListener listener) {
        itemView.setOnTouchListener(listener);
    }
}
