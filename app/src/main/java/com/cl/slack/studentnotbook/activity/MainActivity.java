package com.cl.slack.studentnotbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.bean.Memorandum;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.data.GradesData;
import com.cl.slack.studentnotbook.data.NoteData;
import com.cl.slack.studentnotbook.data.StudentData;
import com.cl.slack.studentnotbook.manager.IGradesManager;
import com.cl.slack.studentnotbook.manager.IMemorandumManeger;
import com.cl.slack.studentnotbook.manager.IStudentManager;
import com.cl.slack.studentnotbook.widget.NoteAdapter;
import com.cl.slack.studentnotbook.widget.StudentAdapter;

import java.util.List;

public class MainActivity extends BaseActivity {

    private final static int REQ_GRADES = 0x100;
    private final static int REQ_STUDENT = 0x101;
    private static final String TAG = "Student";

    NoteData mNoteData = NoteData.data;
    GradesData mGradesData = GradesData.data;
    StudentData mStudentData = StudentData.data;

    private StudentAdapter mStudentAdapter;
    private ArrayAdapter<String> mSpinnerAdapter;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        testGrades();
        initView();

    }

    private void init() {

        List<Grades> grades = mGradesManager.findAllGrades();
        mGradesData.addAll(grades);
        List<Memorandum> data = mMemorandumManeger.findMemorandumToday();
        mNoteData.addAll(data);
    }

    private void initView() {

        RecyclerView studentList = (RecyclerView) findViewById(R.id.main_note_list);
        studentList.setLayoutManager(new LinearLayoutManager(this));
        mStudentAdapter = new StudentAdapter(studentList, mStudentData);
        studentList.setAdapter(mStudentAdapter);

        mSpinner = (Spinner) findViewById(R.id.main_note_spinner);
        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mGradesData.obtainSpinnerData());
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.i(TAG, "onItemSelected: " + pos);
                bindStudentByGrades(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void bindStudentByGrades(int pos) {
        // show progress
        String id = mGradesData.findIdByIndex(pos);
        List<Student> students = mStudentManager.findStudentByClass(id);
        mStudentData.addAll(students);
        mStudentAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_grades:
                startNewClassForResult(GradesActivity.class, REQ_GRADES);
                break;
            case R.id.menu_student:
                startNewClassForResult(StudentActivity.class, REQ_STUDENT);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_GRADES:
                mSpinnerAdapter.clear();
                mSpinnerAdapter.addAll(mGradesData.obtainSpinnerData());
                break;
            case REQ_STUDENT:
                bindStudentByGrades(mSpinner.getSelectedItemPosition());
                break;
        }
    }

    private void testGrades() {
        IGradesManager gradesManager = IGradesManager.manager;

        gradesManager.addGrades(new Grades("一年级0班"));
        gradesManager.addGrades(new Grades("一年级1班"));
        Grades grades2 =new Grades("一年级2班");
        gradesManager.addGrades(grades2);
        Grades grades3 = new Grades("一年级3班");
        gradesManager.addGrades(grades3);

        gradesManager.deleteGrades(grades3);
        List<Grades> list = gradesManager.findGradesByName(grades3.name);
        Log.i(TAG, "grades3 not exits: " + (list.size() == 0));

        grades2.name = "一年级2班_修改";
        gradesManager.updateGrades(grades2);
        List<Grades> lists = gradesManager.findAllGrades();
        for (Grades grades : lists) {
            Log.i(TAG, "grades: " + grades.toString());
        }

        IStudentManager studentManager = IStudentManager.manager;
        studentManager.addStudent(new Student("slack", "slack", lists.get(0)));
        studentManager.addStudent(new Student("slack1", "slack1", lists.get(1)));
        studentManager.addStudent(new Student("slack2", "slack2", lists.get(1)));
        List<Student> students = studentManager.findStudentByName("s");
        for (Student student : students) {
            Log.i(TAG, "student: " + student.toString());
        }

        IMemorandumManeger memorandumManeger = IMemorandumManeger.manager;
        memorandumManeger.addMemorandum(new Memorandum("这个是用来评论的", students.get(0)));
        memorandumManeger.addMemorandum(new Memorandum("你管我呀，我乐意，哈哈哈哈", students.get(0)));
        List<Memorandum> memorandums = memorandumManeger.findAllMemorandum();
        for (Memorandum memorandum : memorandums) {
            Log.i(TAG, "memorandum: " + memorandum.toString());
        }
    }
}
