package com.cl.slack.studentnotbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
}
