package com.cl.slack.studentnotbook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by slack
 * on 17/12/22 下午4:06
 */

public class DataOperater {

    private static final String TAG = "Student";

    private static final String NAME_GRADES = "Grades";
    private static final String NAME_STUDENT = "Student";
    private static final String NAME_MEMORANDUM = "Memorandum";

    public static DataOperater instance = new DataOperater();
    private DataOperater() {
    }

    private SQLiteDatabase mDatabase;

    public void init(Context context) {
        if(mDatabase == null) {
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
            mDatabase = helper.getWritableDatabase();
        }
    }

    /**
     * 添加数据
     */
    public boolean addGrades(Object...values) {
        return addData(NAME_GRADES, values, "values(?,?,?)");
    }

    public boolean addStudent(Object...values) {
        return addData(NAME_STUDENT, values, "values(?,?,?,?,?)");
    }

    public boolean addMemorandum(Object...values) {
        return addData(NAME_MEMORANDUM, values, "values(?,?,?,?,?)");
    }

    private boolean addData(String table, Object[] values, String operates) {
        String INSERT = "insert into " + table + " " +
                operates;
        try {
            mDatabase.execSQL(INSERT, values);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 删除数据
     */
    public boolean deleteGrades(String condition) {
        return deleteData(NAME_GRADES, condition);
    }

    public boolean deleteStudent(String condition) {
        return deleteData(NAME_STUDENT, condition);
    }

    public boolean deleteMemorandum(String condition) {
        return deleteData(NAME_MEMORANDUM, condition);
    }

    private boolean deleteData(String table, String condition) {
        String DELETE = "delete from " + table + " where " + condition;
        Log.i(TAG, "deleteData: "+DELETE);
        try {
            mDatabase.execSQL(DELETE);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新数据
     */
    public boolean updateGrades(String values, String condition) {
        return updateData(NAME_GRADES, values, condition);
    }

    public boolean updateStudent(String values, String condition) {
        return updateData(NAME_STUDENT, values, condition);
    }

    public boolean updateMemorandum(String values, String condition) {
        return updateData(NAME_MEMORANDUM, values, condition);
    }

    private boolean updateData(String table, String values, String condition) {
        String UPDATE = "update " + table + " set " + values + " where " + condition;
        Log.i(TAG, "updateData: "+UPDATE);
        try {
            mDatabase.execSQL(UPDATE);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询数据
     */
    public Cursor selectGrades(String values, String condition) {
        return selectData(NAME_GRADES, values, condition, " ORDER BY update_data DESC");
    }

    public Cursor selectStudent(String condition) {
        return selectData(NAME_STUDENT, "*", condition, " GROUP BY grades_id ORDER BY update_data DESC");
    }

    public Cursor selectStudent(String values, String condition, String order) {
        return selectData(NAME_STUDENT, values, condition, order);
    }

    public Cursor selectMemorandum(String condition) {
        return selectData(NAME_MEMORANDUM, "*", condition, " ORDER BY update_data DESC");
    }

    public Cursor selectMemorandum(String values, String condition, String order) {
        return selectData(NAME_MEMORANDUM, values, condition, order);
    }

    private Cursor selectData(String table, String values, String condition, String order) {
        Cursor cursor;
        String SELECT;
        if (TextUtils.isEmpty(condition)) {
            SELECT = "select " + values + " from " + table;
        } else {
            SELECT = "select " + values + " from " + table + " where " + condition;
        }

        if(!TextUtils.isEmpty(order)) {
            SELECT += order;
        }
        Log.i(TAG, "selectData: -------"+SELECT);
        cursor = mDatabase.rawQuery(SELECT, null);
        return cursor;
    }

}
