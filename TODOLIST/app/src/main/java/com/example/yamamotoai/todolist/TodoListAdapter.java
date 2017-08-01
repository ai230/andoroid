package com.example.yamamotoai.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yamamotoai on 2017-07-30.
 */

public class TodoListAdapter extends BaseAdapter {

    private Context context;
    private List<TODO> todoList;
//    TextView t1;
//    TextView t2;

    public TodoListAdapter(Context context, List<TODO> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        View list;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            list = new View(context);
            list = inflater.inflate(R.layout.list_row, null);
            TextView tv1 = (TextView) list.findViewById(R.id.textview_list_row1);
            TextView tv2 = (TextView) list.findViewById(R.id.textview_list_row2);
            tv1.setText(todoList.get(position).getTitle());
            tv2.setText(todoList.get(position).getTitle());
        } else {
            list = (View) convertView;
        }
        return list;

//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            t1 = (TextView) itemView.findViewById(R.id.textview_list_row1);
//            t2 = (TextView) itemView.findViewById(R.id.textview_list_row2);
//        }
//    }
//
//    public TodoListAdapter(List<TODO> totoList){
//        this.todoList = totoList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        //create a object of LayoutInflater
//        LayoutInflater inflater = LayoutInflater.from(context);
//        //create layout for textview
//        View view = inflater.inflate(R.layout.list_row,parent,false);
//        //need to pass this view to NumberViewHolder
//        MyViewHolder viewHolder = new MyViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        TODO todo = todoList.get(position);
//        t1.setText(todo.getTitle());
//        t2.setText(todo.getTitle());
//    }
//
//    @Override
//    public int getItemCount() {
//        return todoList.size();
//    }

    }
}
