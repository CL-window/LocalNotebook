package com.cl.slack.studentnotbook.data;

import com.cl.slack.studentnotbook.bean.Grades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slack
 * on 17/12/23 下午1:56
 */

public class GradesData extends Data<Grades>{

    public static GradesData data = new GradesData();

    private GradesData() {
    }

    public String findIdByIndex(int index) {
        if(vaild(index)) {
            return list.get(index).getId();
        }
        return "";
    }

    public String getName(int index) {
        if(vaild(index)) {
            return list.get(index).name;
        }
        return "";
    }

    public List<String> obtainSpinnerData() {
        List<String> result = new ArrayList<>();
        for (Grades grades : list) {
            result.add(grades.name);
        }
        return result;
    }
 }
