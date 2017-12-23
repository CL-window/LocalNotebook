package com.cl.slack.studentnotbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Memorandum;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.data.NoteData;
import com.cl.slack.studentnotbook.widget.NoteAdapter;

import java.util.List;

public class NotePageActivity extends BaseActivity {

    public static String KEY_STUDENT_ID = "student_id";
    NoteData mNoteData = NoteData.data;
    NoteAdapter mNoteAdapter;
    EditText mContentText;
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if(intent == null) {
            return;
        }
        String id = intent.getStringExtra(KEY_STUDENT_ID);
        mStudent = mStudentManager.findStudentById(id);
        List<Memorandum> data = mMemorandumManeger.findAllMemorandumByStudent(mStudent);
        mNoteData.addAll(data);

        mContentText = findViewById(R.id.memorandum_content);

        String name = "CH: " + mStudent.nameCH + "\n EN: " + mStudent.nameEN;
        ((TextView) findViewById(R.id.memorandum_student_name)).setText(name);

        findViewById(R.id.memorandum_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.memorandum_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mNoteAdapter = new NoteAdapter(recyclerView);
        recyclerView.setAdapter(mNoteAdapter);
        mNoteAdapter.setCallback(new NoteAdapter.Callback() {
            @Override
            public void onClick(Memorandum memorandum) {
                onNoteClick(memorandum);
            }
        });
    }

    private void onNoteClick(Memorandum memorandum) {
        new NoteEditDialog(this, memorandum)
                .setCallback(mEditCallback)
                .showAtLocation(mContentText, Gravity.CENTER, 0, 0);
    }

    private NoteEditDialog.Callback mEditCallback = new NoteEditDialog.Callback() {
        @Override
        public void onDelete(Memorandum memorandum) {
            boolean success = mMemorandumManeger.deleteMemorandum(memorandum);
            if(success) {
                int index = mNoteData.remove(memorandum);
                mNoteAdapter.notifyItemRemoved(index);
            }
            showToast("删除备忘录 " + (success ? "Success" : "Failed"));
        }

        @Override
        public void onUpdate(Memorandum memorandum, String msg) {
            if(TextUtils.isEmpty(msg)) {
                showToast("随便写点啥吧");
                return;
            }
            memorandum.content = msg;
            boolean success = mMemorandumManeger.updateMemorandum(memorandum);
            if(success) {
                mNoteAdapter.notifyItemChanged(mNoteData.indexOf(memorandum));
            }

            showToast("更新备忘录 " + (success ? "Success" : "Failed"));
        }
    };

    private void addNote() {
        String content = mContentText.getText().toString();
        if(TextUtils.isEmpty(content)) {
            showToast("随便写点啥吧");
            return;
        }
        Memorandum memorandum = new Memorandum(content, mStudent);
        boolean success = mMemorandumManeger.addMemorandum(memorandum);
        if(success) {
            mContentText.setText("");
            mNoteData.insert(memorandum);
            mNoteAdapter.notifyItemInserted(0);
        }

        showToast("新增备忘录 " + (success ? "Success" : "Failed"));
    }
}
