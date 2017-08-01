package com.example.yamamotoai.todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-07-30.
 */

public class TODO  implements Serializable {

    String date, title, group,content;
    List<TODO> todoList;

    public TODO(String date, String title, String group, String content) {
        this.date = date;
        this.title = title;
        this.group = group;
        this.content = content;
    }

    public List<TODO> getTodoList(List<TODO> todoList){
        todoList = new ArrayList<>();
        return todoList;
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
