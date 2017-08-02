package com.example.yamamotoai.midproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class TODO implements Serializable {

    String date, title, group,content;
//    List<TODO> todoList = new ArrayList<>();

    public TODO(String date, String title, String group, String content) {
        this.date = date;
        this.title = title;
        this.group = group;
        this.content = content;
    }

//    public List<TODO> getTodoList(List<TODO> todoList){
//        this.todoList = todoList;
////        todoList = new ArrayList<>();
//        return todoList;
//    }
//
//    public void addTODO(TODO todo){
//        todoList.add(todo);
//    }

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
