package com.cl.slack.studentnotbook.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.data.GradesData;

/**
 * Created by slack
 * on 17/12/23 下午3:07
 */

public class GradesAdapter extends BaseAdapter<GradesAdapter.VH>{

    private GradesData mGradesData;
    public GradesAdapter(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
        mGradesData = GradesData.data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(inflateView(R.layout.layout_grades_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final Grades grades = mGradesData.get(position);
        if(grades == null) {
            return;
        }
        holder.bindView(grades.name);
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mCallback != null) {
                    mCallback.onLongClick(grades);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGradesData.size();
    }

    class VH extends BaseViewHolder{

        TextView name;
        VH(View itemView) {
            super(itemView);
            name = (TextView) findView(R.id.grades_item_name);
        }

        void bindView(String msg) {
            name.setText(msg);
        }
    }


    private Callback mCallback;
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void onLongClick(Grades grades);
    }
}
