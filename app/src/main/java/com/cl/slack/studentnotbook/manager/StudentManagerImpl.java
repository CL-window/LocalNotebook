package com.cl.slack.studentnotbook.manager;

import android.database.Cursor;

import com.cl.slack.studentnotbook.bean.Grades;
import com.cl.slack.studentnotbook.bean.Student;
import com.cl.slack.studentnotbook.database.DataOperater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午3:30
 */

public class StudentManagerImpl implements IStudentManager {

    private DataOperater mDataOperater = DataOperater.instance;
    private IGradesManager mGradesManager = IGradesManager.manager;

    static StudentManagerImpl instance = new StudentManagerImpl();
    private StudentManagerImpl() {
    }

    @Override
    public boolean addStudent(Student student) {
        return mDataOperater.addStudent(student.getId(), student.nameCH, student.nameEN, student.grades.getId());
    }

    @Override
    public boolean deleteStudent(Student student) {
        return deleteStudentById(student.getId());
    }

    @Override
    public boolean deleteStudentById(String id) {
        return mDataOperater.deleteStudent("id ='" + id + "'");
    }

    @Override
    public boolean updateStudent(Student student) {
        return mDataOperater.updateStudent("name_ch = '" + student.nameCH +
                        "', name_en = '" + student.nameEN + "', grades_id = '" + student.grades.getId() + "'",
                "id ='" + student.getId() + "'");
    }

    @Override
    public Student findStudentById(String id) {
        return findStudentById(id, true);
    }

    @Override
    public Student findStudentById(String id, boolean detail) {
        Cursor cursor = mDataOperater.selectStudent("id ='" + id + "'");
        if(cursor.moveToFirst()) {
            return newStudent(cursor, detail);
        }
        return null;
    }

    private Student newStudent(Cursor cursor, boolean detail) {
        String gradesId = cursor.getString(3);
        Grades grades = Grades.genGrades(gradesId);
        if(detail) {
            grades = mGradesManager.findGradesById(gradesId);
        }
        return Student.genStudent(cursor, grades);
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return findStudentByName(name, true);
    }

    @Override
    public List<Student> findStudentByName(String name, boolean detail) {
        List<Student> students = new ArrayList<>();
        Cursor cursor = mDataOperater.selectStudent("name_ch LIKE '%" + name + "%' OR name_en LIKE '%" + name + "%'");
        if(cursor.moveToFirst()) {
            do{
                students.add(newStudent(cursor, detail));
            } while (cursor.moveToNext());
        }
        return students;
    }

    @Override
    public List<Student> findStudentByClass(String gradesId) {
        return findStudentByClass(gradesId, true);
    }

    @Override
    public List<Student> findStudentByClass(String gradesId, boolean detail) {
        List<Student> students = new ArrayList<>();
        Cursor cursor = mDataOperater.selectStudent("grades_id ='" + gradesId + "'");
        if(cursor.moveToFirst()) {
            do{
                students.add(newStudent(cursor, detail));
            } while (cursor.moveToNext());
        }
        return students;
    }

    @Override
    public List<Student> findAllStudent() {
        return findAllStudent(true);
    }

    @Override
    public List<Student> findAllStudent(boolean detail) {
        List<Student> students = new ArrayList<>();
        Cursor cursor = mDataOperater.selectStudent("");
        if(cursor.moveToFirst()) {
            do{
                students.add(newStudent(cursor, detail));
            } while (cursor.moveToNext());
        }
        return students;
    }
}
