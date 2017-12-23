package com.cl.slack.studentnotbook.bean;

import android.database.Cursor;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by slack
 * on 17/12/22 下午3:28
 */

public class Memorandum extends Base{
    public String content;
    public String data;// 日期
    public Student student;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public Memorandum(String content, Student student) {
        super();
        this.content = content;
        this.student = student;
        this.data = formatter.format(new Date());
    }

    private Memorandum(String id, String content, String data, Student student) {
        super(id);
        this.content = content;
        this.data = data;
        this.student = student;
    }

    public static Memorandum genMemorandum(@NonNull Cursor cursor, Student student) {
        return new Memorandum(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), student);
    }

    @Override
    public String toString() {
        return "{[Memorandum]: " +
                " id: " + getId() +
                " content: " + content +
                " data: " + data +
                " student: " + (student == null ? "null" : student.toString() ) +
                "}";
    }
}
