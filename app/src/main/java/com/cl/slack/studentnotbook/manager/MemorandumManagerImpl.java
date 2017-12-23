package com.cl.slack.studentnotbook.manager;

import android.database.Cursor;

import com.cl.slack.studentnotbook.bean.Memorandum;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.database.DataOperater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午3:42
 */

public class MemorandumManagerImpl implements IMemorandumManeger {

    private DataOperater mDataOperater = DataOperater.instance;
    private IStudentManager mStudentManager = IStudentManager.manager;

    static MemorandumManagerImpl instance = new MemorandumManagerImpl();
    private MemorandumManagerImpl() {
    }

    @Override
    public boolean addMemorandum(Memorandum memorandum) {
        return mDataOperater.addMemorandum(memorandum.getId(), memorandum.content, memorandum.data, currentTime(), memorandum.student.getId());
    }

    @Override
    public boolean deleteMemorandum(Memorandum memorandum) {
        return deleteMemorandumById(memorandum.getId());
    }

    @Override
    public boolean deleteMemorandumById(String id) {
        return mDataOperater.deleteMemorandum("id ='" + id + "'");
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }

    @Override
    public boolean updateMemorandum(Memorandum memorandum) {
        return mDataOperater.updateMemorandum("content = '" + memorandum.content +
                        "', data = '" + memorandum.data + "', student_id = '" + memorandum.student.getId() +
                "', updateData = '" + currentTime() + "'",
                "id ='" + memorandum.getId() + "'");
    }

    @Override
    public List<Memorandum> findMemorandumByStudent(Student student) {
        return findMemorandumByStudent(student, true);
    }

    private Memorandum newMemorandum(Cursor cursor, boolean detail) {
        String id = cursor.getString(4);
        Student student = Student.genStudent(id);
        if(detail) {
            student = mStudentManager.findStudentById(id, true);
        }
        return Memorandum.genMemorandum(cursor, student);
    }


    @Override
    public List<Memorandum> findMemorandumByStudent(Student student, boolean detail) {
        List<Memorandum> memorandums = new ArrayList<>();
        Cursor cursor = mDataOperater.selectMemorandum("student_id = '" + student.getId() + "'");
        if(cursor.moveToFirst()) {
            do{
                memorandums.add(newMemorandum(cursor, detail));
            } while (cursor.moveToNext());
        }
        return memorandums;
    }

    @Override
    public List<Memorandum> findMemorandumByData(String data) {
        return findMemorandumByData(data, true);
    }

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<Memorandum> findMemorandumToday() {
        return findMemorandumByData(formatter.format(new Date()), true);
    }

    @Override
    public List<Memorandum> findMemorandumByData(String data, boolean detail) {
        List<Memorandum> memorandums = new ArrayList<>();
        Cursor cursor = mDataOperater.selectMemorandum("data = '" + data + "'");
        if(cursor.moveToFirst()) {
            do{
                memorandums.add(newMemorandum(cursor, detail));
            } while (cursor.moveToNext());
        }
        return memorandums;
    }

    @Override
    public List<Memorandum> findMemorandum(Student student, String data) {
        return findMemorandum(student, data, true);
    }

    @Override
    public List<Memorandum> findMemorandum(Student student, String data, boolean detail) {
        List<Memorandum> memorandums = new ArrayList<>();
        Cursor cursor = mDataOperater.selectMemorandum("student_id = '" + student.getId() + "' AND data = '" + data + "'");
        if(cursor.moveToFirst()) {
            do{
                memorandums.add(newMemorandum(cursor, detail));
            } while (cursor.moveToNext());
        }
        return memorandums;
    }

    @Override
    public List<Memorandum> findAllMemorandum() {
        return findAllMemorandum(true);
    }

    @Override
    public List<Memorandum> findAllMemorandum(boolean detail) {
        List<Memorandum> memorandums = new ArrayList<>();
        Cursor cursor = mDataOperater.selectMemorandum("");
        if(cursor.moveToFirst()) {
            do{
                memorandums.add(newMemorandum(cursor, detail));
            } while (cursor.moveToNext());
        }
        return memorandums;
    }
}
