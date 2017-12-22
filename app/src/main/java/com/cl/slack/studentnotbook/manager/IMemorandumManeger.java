package com.cl.slack.studentnotbook.manager;

import com.cl.slack.studentnotbook.bean.Memorandum;
import com.cl.slack.studentnotbook.bean.Student;

import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午3:39
 */

public interface IMemorandumManeger {

    IMemorandumManeger manager = MemorandumManagerImpl.instance;

    boolean addMemorandum(Memorandum memorandum);

    boolean deleteMemorandum(Memorandum memorandum);

    boolean deleteMemorandumById(String id);

    boolean updateMemorandum(Memorandum memorandum);

    List<Memorandum> findMemorandumByStudent(Student student);

    List<Memorandum> findMemorandumByStudent(Student student, boolean detail);

    List<Memorandum> findMemorandumByData(String data);

    List<Memorandum> findMemorandumByData(String data, boolean detail);

    List<Memorandum> findMemorandum(Student student, String data);

    List<Memorandum> findMemorandum(Student student, String data, boolean detail);

    List<Memorandum> findAllMemorandum();

    List<Memorandum> findAllMemorandum(boolean detail);

}
