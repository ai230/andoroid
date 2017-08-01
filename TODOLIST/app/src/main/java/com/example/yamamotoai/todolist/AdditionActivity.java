package com.example.yamamotoai.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-07-30.
 */

public class AdditionActivity extends AppCompatActivity{

//    List<TODO> todoList;
//    AdditionInterface additioninterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

    }

    public void onClickCancel(View view){
        Log.d("---", "click1");
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);

    }

    public void onClickSave(View view){
        Log.d("---", "click2");
        TODO todo = new TODO("2017/07/10", "title1", "group1", "content1");


//        if(todoList == null){
//            todoList = todo.getTodoList(todoList);
//        }
//        todoList.add(todo);
//        Log.d("---", String.valueOf(todoList.size()));
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TodoDate", todo.getDate());
        intent.putExtra("TodoTitle", todo.getTitle());
        intent.putExtra("TodoGroup", todo.getGroup());
        intent.putExtra("TodoContent", todo.getContent());
        intent.putExtra("MyClass", todo);
        startActivity(intent);
    }


    public void onClickDelete(View view){
        Log.d("---", "click3");
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

}
