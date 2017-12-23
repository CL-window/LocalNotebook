package com.cl.slack.studentnotbook.data;

import android.support.annotation.Nullable;

import com.cl.slack.studentnotbook.bean.Grades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slack
 * on 17/12/23 下午2:17
 */

public abstract class Data<T> {
    protected final List<T> list = new ArrayList<>();

    public void addAll(List<T> l) {
        this.list.clear();
        this.list.addAll(l);
    }

    protected boolean vaild(int index) {
        return index >= 0 && index < size();
    }

    public int size(){
        return list.size();
    }

    @Nullable
    public T get(int position) {
        if(vaild(position)) {
            return list.get(position);
        }
        return null;
    }

    public void insert(T t) {
        list.add(0, t);
    }

    public int indexOf(T t) {
        return list.indexOf(t);
    }

    public int remove(T t) {
        int index = list.indexOf(t);
        if(index != -1) {
            list.remove(t);
        }
        return index;
    }
}
