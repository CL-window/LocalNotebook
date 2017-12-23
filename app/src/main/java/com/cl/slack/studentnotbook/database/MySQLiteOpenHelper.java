package com.cl.slack.studentnotbook.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by slack
 * on 17/12/22 下午3:49
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "Student";

    private String CREATE_GRADES = "create table Grades (" +
            "id CHAR(40) primary key NOT NULL," +
            "name CHAR(40) NOT NULL, " +
            "updateData CHAR(20) NOT NULL"+
            ")";

    private String CREATE_STUDENT = "create table Student (" +
            "id CHAR(40) primary key NOT NULL," +
            "name_ch CHAR(10) NOT NULL,"+
            "name_en CHAR(40) NOT NULL,"+
            "grades_id CHAR(40) NOT NULL," +
            "updateData CHAR(20) NOT NULL," +
            " FOREIGN KEY (grades_id) REFERENCES Grades(id) " +
            ")";

    private String CREATE_MEMORANDUM = "create table Memorandum (" +
            "id CHAR(40) primary key NOT NULL," +
            "content CHAR(255) NOT NULL,"+
            "data CHAR(10) NOT NULL,"+
            "updateData CHAR(20) NOT NULL,"+
            "student_id CHAR(40) NOT NULL," +
            " FOREIGN KEY (student_id) REFERENCES Student(id) " +
            ")";

    public MySQLiteOpenHelper(Context context) {
        this(context, "student.db", null, 1);
    }

    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version, new MyDatabaseErrorHandler());
    }

    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    static class MyDatabaseErrorHandler implements DatabaseErrorHandler {

        @Override
        public void onCorruption(SQLiteDatabase sqLiteDatabase) {
            Log.i(TAG, "onCorruption...");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        execSQL(sqLiteDatabase, CREATE_GRADES);
        execSQL(sqLiteDatabase, CREATE_STUDENT);
        execSQL(sqLiteDatabase, CREATE_MEMORANDUM);
        Log.i(TAG, "SQLiteOpenHelper onCreate...");
    }

    private void execSQL(SQLiteDatabase database, String sql) {
        try {
            database.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "SQLiteOpenHelper onUpgrade...");
    }
}
