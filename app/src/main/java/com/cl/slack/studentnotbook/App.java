package com.cl.slack.studentnotbook;

import android.app.Application;

import com.cl.slack.studentnotbook.database.DataOperater;
import com.cl.slack.studentnotbook.util.ScreenHelper;

/**
 * Created by slack
 * on 17/12/23 下午2:44
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ScreenHelper.helper.init(this);
        DataOperater.instance.init(this);
    }
}
