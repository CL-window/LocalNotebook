package com.cl.slack.studentnotbook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cl.slack.studentnotbook.data.GradesData;
import com.cl.slack.studentnotbook.data.NoteData;
import com.cl.slack.studentnotbook.data.StudentData;
import com.cl.slack.studentnotbook.manager.IGradesManager;
import com.cl.slack.studentnotbook.manager.IMemorandumManeger;
import com.cl.slack.studentnotbook.manager.IStudentManager;

/**
 * Created by slack
 * on 17/12/22 下午7:52
 */

public abstract class BaseActivity extends AppCompatActivity {

    IMemorandumManeger mMemorandumManeger = IMemorandumManeger.manager;
    IStudentManager mStudentManager = IStudentManager.manager;
    IGradesManager mGradesManager = IGradesManager.manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void startNewClass(Class zlass){
        startNewIntent(new Intent(this, zlass));
    }

    protected void startNewIntent(Intent intent){
        startActivity(intent);
    }

    protected void startNewClassForResult(Class zlass, int code){
        startNewIntentForResult(new Intent(this, zlass), code);
    }

    protected void startNewIntentForResult(Intent intent, int code){
        startActivityForResult(intent, code);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
