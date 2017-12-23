package com.cl.slack.studentnotbook.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.data.GradesData;
import com.cl.slack.studentnotbook.widget.GradesAdapter;

public class GradesActivity extends BaseActivity {

    EditText nameText;
    GradesAdapter mGradesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        initView();
    }

    private void initView() {
        nameText = findViewById(R.id.grades_name);
        findViewById(R.id.grades_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGrades();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.grades_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGradesAdapter = new GradesAdapter(recyclerView);
        recyclerView.setAdapter(mGradesAdapter);
        mGradesAdapter.setCallback(new GradesAdapter.Callback() {
            @Override
            public void onLongClick(Grades grades) {
                deleteGrades(grades);
            }
        });
    }

    private void deleteGrades(final Grades grades) {
        new  AlertDialog.Builder(this)
                .setMessage("是否删除" + grades.name + " ?")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean success = mGradesManager.deleteGrades(grades);
                        if(success) {
                            int index = GradesData.data.remove(grades);
                            mGradesAdapter.notifyItemRemoved(index);
                        }
                    }
                })
                .show();
    }

    private void addGrades() {
        String name = nameText.getText().toString().trim();
        if(TextUtils.isEmpty(name)) {
            showToast("随便起个名字吧");
            return;
        }
        Grades grades = new Grades(name);
        boolean success = mGradesManager.addGrades(grades);
        if(success) {
            nameText.setText("");
            GradesData.data.insert(grades);
            mGradesAdapter.notifyItemInserted(0);
        }

        showToast("新增班级 " + (success ? "Success" : "Failed"));
    }
}
