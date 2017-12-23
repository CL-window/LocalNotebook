package com.cl.slack.studentnotbook.data;

import android.support.annotation.Nullable;

import com.cl.slack.studentnotbook.bean.Memorandum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午7:57
 */

public class NoteData extends Data<Memorandum>{

    public static NoteData data = new NoteData();
    private NoteData() {
    }
}
