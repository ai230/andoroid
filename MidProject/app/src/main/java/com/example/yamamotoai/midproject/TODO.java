package com.example.yamamotoai.midproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class TODO implements Serializable {

    int id;
    String date, title, group,content;


    public TODO(int id, String date, String title, String group, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.group = group;
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    public String getContent() {
        return content;
    }

}
