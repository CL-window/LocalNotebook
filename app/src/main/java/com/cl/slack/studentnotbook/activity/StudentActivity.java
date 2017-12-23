package com.cl.slack.studentnotbook.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.data.GradesData;
import com.cl.slack.studentnotbook.data.StudentData;
import com.cl.slack.studentnotbook.widget.StudentAdapter;

import java.util.List;

public class StudentActivity extends BaseActivity {

    EditText nameTextCH,nameTextEN;
    Spinner mSpinner;
    StudentAdapter mStudentAdapter;
    StudentData mAllStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initView();
    }

    private void initView() {

        List<Student> students = mStudentManager.findAllStudent();
        mAllStudent = new StudentData();
        mAllStudent.addAll(students);

        nameTextCH = findViewById(R.id.student_name_ch);
        nameTextEN = findViewById(R.id.student_name_en);
        findViewById(R.id.student_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });

        mSpinner = (Spinner) findViewById(R.id.student_grades_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GradesData.data.obtainSpinnerData());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinner.setAdapter(adapter);

        RecyclerView recyclerView = findViewById(R.id.student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStudentAdapter = new StudentAdapter(recyclerView, mAllStudent);
        recyclerView.setAdapter(mStudentAdapter);
        mStudentAdapter.setCallback(new StudentAdapter.Callback() {
            @Override
            public void onLongClick(Student student) {
                deleteStudent(student);
            }
        });
    }

    private void deleteStudent(final Student student) {
        new  AlertDialog.Builder(this)
                .setMessage("是否删除 \n" + student.nameCH + " " + student.nameEN + " ?")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean success = mStudentManager.deleteStudent(student);
                        if(success) {
                            int index = mAllStudent.remove(student);
                            mStudentAdapter.notifyItemRemoved(index);
                        }
                    }
                })
                .show();
    }

    private void addStudent() {
        String ch = nameTextCH.getText().toString().trim();
        if(TextUtils.isEmpty(ch)) {
            showToast("随便填一个中文名吧");
            return;
        }
        String en = nameTextEN.getText().toString().trim();
        if(TextUtils.isEmpty(en)) {
            showToast("随便填一个英文名吧");
            return;
        }

        Grades grades = GradesData.data.get(mSpinner.getSelectedItemPosition());
        Student student = new Student(ch, en, grades);
        boolean success = mStudentManager.addStudent(student);
        if(success) {
            nameTextCH.setText("");
            nameTextEN.setText("");

            mAllStudent.insert(student);
            mStudentAdapter.notifyItemInserted(0);
        }

        showToast("新增学生 " + (success ? "Success" : "Failed"));
    }
}
