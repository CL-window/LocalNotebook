package com.cl.slack.studentnotbook.bean;

import java.util.UUID;

/**
 * Created by slack
 * on 17/12/22 下午5:09
 */

class Base {
    private String id;

    Base() {
        this(UUID.randomUUID().toString());
    }

    Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
