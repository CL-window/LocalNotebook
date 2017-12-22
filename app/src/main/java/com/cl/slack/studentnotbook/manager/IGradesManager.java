package com.cl.slack.studentnotbook.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cl.slack.studentnotbook.bean.Grades;

import java.util.List;

/**
 * Created by slack
 * on 17/12/22 下午3:32
 */

public interface IGradesManager {

    IGradesManager manager = GradesManagerImpl.instance;

    boolean addGrades(Grades grades);

    boolean deleteGrades(Grades grades);

    boolean deleteGradesById(String id);

    /**
     * 改名字
     */
    boolean updateGrades(Grades grades);

    @Nullable
    Grades findGradesById(String id);

    List<Grades> findAllGrades();

    List<Grades> findGradesByName(String name);
}
