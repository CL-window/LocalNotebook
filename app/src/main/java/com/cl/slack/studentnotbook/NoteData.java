package com.cl.slack.studentnotbook;

import android.support.annotation.Nullable;

import com.cl.slack.studentnotbook.bean.Memorandum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午7:57
 */

public class NoteData {
    List<Memorandum> data = new ArrayList<>();

    void addAll(List<Memorandum> list) {
        this.data.addAll(list);
    }

    public int size() {
        return data.size();
    }

    private boolean vaild(int index) {
        return index >= 0 && index < size();
    }


    @Nullable
    public Memorandum get(int position) {
        if(vaild(position)) {
            return data.get(position);
        }
        return null;
    }
}
