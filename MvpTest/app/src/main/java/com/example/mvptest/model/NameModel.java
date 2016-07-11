package com.example.mvptest.model;

import java.io.Serializable;

/**
 * Created by wangbs on 16/7/11.
 */
public class NameModel implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
