package com.cl.slack.studentnotbook.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.data.StudentData;

/**
 * Created by slack
 * on 17/12/23 下午2:09
 */

public class StudentAdapter extends BaseAdapter<StudentAdapter.VH>{

    private StudentData mStudentData;
    public StudentAdapter(@NonNull RecyclerView recyclerView, StudentData data) {
        super(recyclerView);
        mStudentData = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(inflateView(R.layout.layout_student, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final Student student = mStudentData.get(position);
        holder.bindView(student);
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mCallback != null) {
                    mCallback.onLongClick(student);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudentData.size();
    }

    class VH extends BaseViewHolder{

        TextView nameCH;
        TextView nameEN;
        VH(View itemView) {
            super(itemView);
            nameCH = (TextView) findView(R.id.student_name_ch);
            nameEN = (TextView) findView(R.id.student_name_en);
        }

        void bindView(Student student) {
            nameCH.setText("Name_CH: " + student.nameCH);
            nameEN.setText("Name_EN: " + student.nameEN);
        }
    }

    private Callback mCallback;
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void onLongClick(Student student);
    }
}
