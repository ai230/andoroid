package com.yamamotoai.databindingdemo;

/**
 * Created by yamamotoai on 2017-11-05.
 */

public class User {
    private String name;
    private String surName;
    private String school;


    public User(String name, String surName, String school) {
        this.name = name;
        this.surName = surName;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getSchool() {
        return school;
    }
}
