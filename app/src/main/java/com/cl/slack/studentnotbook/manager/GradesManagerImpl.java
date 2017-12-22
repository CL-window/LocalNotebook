package com.cl.slack.studentnotbook.manager;

import android.database.Cursor;

import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.database.DataOperater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午3:37
 */

public class GradesManagerImpl implements IGradesManager {

    private DataOperater mDataOperater = DataOperater.instance;
    static  GradesManagerImpl instance = new GradesManagerImpl();

    private GradesManagerImpl() {
    }

    @Override
    public boolean addGrades(Grades grades) {
        return mDataOperater.addGrades(grades.getId(), grades.name);
    }

    @Override
    public boolean deleteGrades(Grades grades) {
        return deleteGradesById(grades.getId());
    }

    @Override
    public boolean deleteGradesById(String id) {
        return mDataOperater.deleteGrades("id = '" + id + "'");
    }

    @Override
    public boolean updateGrades(Grades grades) {
        return mDataOperater.updateGrades("name = '" + grades.name + "'","id = '" + grades.getId() + "'");
    }

    @Override
    public Grades findGradesById(String id) {
        Cursor cursor = mDataOperater.selectGrades("*", "id = '" + id + "'");
        if(cursor.moveToFirst()) {
            return Grades.genGrades(cursor);
        }
        return null;
    }

    @Override
    public List<Grades> findAllGrades() {
        List<Grades> grades = new ArrayList<>();
        Cursor cursor = mDataOperater.selectGrades("*", "");
        if (cursor.moveToFirst()) {
            do {
                grades.add(Grades.genGrades(cursor));
            } while (cursor.moveToNext());
        }
        return grades;
    }

    @Override
    public List<Grades> findGradesByName(String name) {
        List<Grades> grades = new ArrayList<>();
        Cursor cursor = mDataOperater.selectGrades("*", "name = '" + name + "'");
        if (cursor.moveToFirst()) {
            do {
                grades.add(Grades.genGrades(cursor));
            } while (cursor.moveToNext());
        }
        return grades;
    }
}