package com.cl.slack.studentnotbook.manager;

import com.cl.slack.studentnotbook.bean.Student;

import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午3:29
 */

public interface IStudentManager {

    IStudentManager manager = StudentManagerImpl.instance;

    boolean addStudent(Student student);

    boolean deleteStudent(Student student);

    boolean deleteStudentById(String id);

    boolean updateStudent(Student student);

    Student findStudentById(String id);

    /**
     *
     * @param detail 是否查询学生里的班级name
     */
    Student findStudentById(String id, boolean detail);

    List<Student> findStudentByName(String name);

    List<Student> findStudentByName(String name, boolean detail);

    List<Student> findStudentByClass(String gradesId);

    List<Student> findStudentByClass(String gradesId, boolean detail);

    List<Student> findAllStudent();

    List<Student> findAllStudent(boolean detail);

}
