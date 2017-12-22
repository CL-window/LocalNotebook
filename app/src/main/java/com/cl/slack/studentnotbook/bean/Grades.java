package com.cl.slack.studentnotbook.bean;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by slack
 * on 17/12/22 下午3:28
 */

public class Grades extends Base {
    public String name;

    public Grades(String name) {
        super();
        this.name = name;
    }

    /**
     * 此方法不建议调用，除非从数据查询时
     */
    private Grades(String id, String name) {
        super(id);
        this.name = name;
    }

    public static Grades genGrades(String id) {
        Grades grades = new Grades("");
        grades.setId(id);
        return grades;
    }

    public static Grades genGrades(@NonNull Cursor cursor) {
        return new Grades(cursor.getString(0), cursor.getString(1));
    }

    @Override
    public String toString() {
        return "{[Grades:]" +
                " id: " + getId() +
                " name: " + name +
                "}";
    }
}
