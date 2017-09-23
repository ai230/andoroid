package com.example.yamamotoai.todolist;

/**
 * Created by yamamotoai on 2017-09-19.
 */

public class Group {

    String group;
    int numOfTodo;
    int numOfDone;

    public Group(){

    };
    public Group(String group, int numOfTodo, int numOfDone) {
        this.group = group;
        this.numOfTodo = numOfTodo;
        this.numOfDone = numOfDone;
    }

    public String getGroup() {
        return group;
    }

    public int getNumOfTodo() {
        return numOfTodo;
    }

    public int getNumOfDone() {
        return numOfDone;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setNumOfTodo(int numOfTodo) {
        this.numOfTodo = numOfTodo;
    }

    public void setNumOfDone(int numOfDone) {
        this.numOfDone = numOfDone;
    }
}
