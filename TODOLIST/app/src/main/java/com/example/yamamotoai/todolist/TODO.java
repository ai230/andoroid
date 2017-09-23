package com.example.yamamotoai.todolist;

import java.io.Serializable;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class TODO  implements Serializable {

    int id;
    String date, title, group, content;
    boolean isDone;

    boolean isSelected;
    public TODO(){};

    public TODO(String date, String title, String group, String content, boolean isDone) {
        this.date = date;
        this.title = title;
        this.group = group;
        this.content = content;
        this.isDone = isDone;
        this.isSelected = false;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public void setDone(boolean done) {
        isDone = done;
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

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isDone() {
        return isDone;
    }
}
