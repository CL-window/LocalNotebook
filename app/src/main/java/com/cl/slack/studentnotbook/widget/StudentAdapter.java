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
    private boolean showGrades;
    public StudentAdapter(@NonNull RecyclerView recyclerView, StudentData data, boolean showGrades) {
        super(recyclerView);
        mStudentData = data;
        this.showGrades = showGrades;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(inflateView(R.layout.layout_student, parent, false), showGrades);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final Student student = mStudentData.get(position);
        holder.bindView(student);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCallback != null) {
                    mCallback.onClick(student);
                }
            }
        });
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

        boolean show;
        TextView gradesName;
        TextView nameCH;
        TextView nameEN;
        VH(View itemView, boolean show) {
            super(itemView);
            gradesName = (TextView) findView(R.id.student_grades_name);
            nameCH = (TextView) findView(R.id.student_name_ch);
            nameEN = (TextView) findView(R.id.student_name_en);
            this.show = show;
        }

        void bindView(Student student) {
            if(show) {
                gradesName.setText(student.grades.name);
            } else {
                gradesName.setText("");
            }
            nameCH.setText("Name_CH: " + student.nameCH);
            nameEN.setText("Name_EN: " + student.nameEN);
        }
    }

    private Callback mCallback;
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public static class Callback {
        protected void onClick(Student student) {

        }
        protected void onLongClick(Student student) {

        }
    }
}
