package com.cl.slack.studentnotbook.bean;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by slack
 * on 17/12/22 下午3:27
 */

public class Student extends Base{
    public String nameCH;
    public String nameEN;
    public Grades grades;

    public Student(String nameCH, String nameEN, Grades grades) {
        super();
        this.nameCH = nameCH;
        this.nameEN = nameEN;
        this.grades = grades;
    }

    private Student(String id, String nameCH, String nameEN, Grades grades) {
        super(id);
        this.nameCH = nameCH;
        this.nameEN = nameEN;
        this.grades = grades;
    }

    public static Student genStudent(String id) {
        Student student = new Student("", "", null);
        student.setId(id);
        return student;
    }

    public static Student genStudent(@NonNull Cursor cursor, Grades grades) {
        return new Student(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), grades);
    }

    @Override
    public String toString() {
        return "{[Student]: " +
                " id: " + getId() +
                " nameCH: " + nameCH +
                " nameEN: " + nameEN +
                " grades: " + (grades == null ? "null" : grades.toString()) +
                "}";
    }
}
