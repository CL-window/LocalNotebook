package com.cl.slack.studentnotbook;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.bean.Memorandum;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.database.DataOperater;
import com.cl.slack.studentnotbook.manager.IGradesManager;
import com.cl.slack.studentnotbook.manager.IMemorandumManeger;
import com.cl.slack.studentnotbook.manager.IStudentManager;
import com.cl.slack.studentnotbook.widget.NoteAdapter;

import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "Student";
    private NoteAdapter mNoteAdapter;
    private NoteData mNoteData = new NoteData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataOperater.instance.init(this);

        initView();

//        testGrades();
    }

    private void initView() {
        List<Memorandum> data = mMemorandumManeger.findMemorandumToday();
        mNoteData.addAll(data);
        RecyclerView noteList = (RecyclerView) findViewById(R.id.main_note_list);
        noteList.setLayoutManager(new GridLayoutManager(this, 2));
        mNoteAdapter = new NoteAdapter(noteList, mNoteData);
        noteList.setAdapter(mNoteAdapter);
    }

    private void testGrades() {
        IGradesManager gradesManager = IGradesManager.manager;

//        gradesManager.addGrades(new Grades("一年级1班"));
//        Grades grades2 =new Grades("一年级2班");
//        gradesManager.addGrades(grades2);
//        Grades grades3 = new Grades("一年级3班");
//        gradesManager.addGrades(grades3);
//
//        gradesManager.deleteGrades(grades3);
//        List<Grades> list = gradesManager.findGradesByName(grades3.name);
//        Log.i(TAG, "grades3 not exits: " + (list.size() == 0));
//
//        grades2.name = "一年级2班_修改";
//        gradesManager.updateGrades(grades2);
        List<Grades> lists = gradesManager.findAllGrades();
        for (Grades grades : lists) {
            Log.i(TAG, "grades: " + grades.toString());
        }

        IStudentManager studentManager = IStudentManager.manager;
//        studentManager.addStudent(new Student("slack", "slack", lists.get(0).getId()));
//        studentManager.addStudent(new Student("slack1", "slack1", lists.get(1).getId()));
        List<Student> students = studentManager.findStudentByName("s");
        for (Student student : students) {
            Log.i(TAG, "student: " + student.toString());
        }

        IMemorandumManeger memorandumManeger = IMemorandumManeger.manager;
//        memorandumManeger.addMemorandum(new Memorandum("这个是用来评论的", students.get(0)));
//        memorandumManeger.addMemorandum(new Memorandum("你管我呀，我乐意，哈哈哈哈", students.get(0)));
        List<Memorandum> memorandums = memorandumManeger.findAllMemorandum();
        for (Memorandum memorandum : memorandums) {
            Log.i(TAG, "memorandum: " + memorandum.toString());
        }
    }
}
