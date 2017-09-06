package com.example.yamamotoai.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yamamotoai on 2017-09-05.
 */

public class AllTodolistAdapter extends BaseAdapter {

    List<TODO> list;
    Context context;

    public AllTodolistAdapter(Context context, List<TODO> list){
        this.context = context;
        this.list = list;
    }

    public class Holder{
        CheckBox checkBox;
        TextView idTextview;
        TextView dateTextview;
        TextView daysTextview;
        TextView titleTextview;
        TextView groupTextview;
        TextView contentTextview;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final TODO todo = list.get(position);
//        View view =  convertView;
        Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_todolist_row, null);
        holder.checkBox = (CheckBox) view.findViewById(R.id.checkbox_second);
        holder.idTextview = (TextView) view.findViewById(R.id.textview_id);
        holder.dateTextview = (TextView) view.findViewById(R.id.textview_date);
        holder.daysTextview = (TextView) view.findViewById(R.id.textview_days);
        holder.titleTextview = (TextView) view.findViewById(R.id.textview_title);
        holder.groupTextview = (TextView) view.findViewById(R.id.textview_group1);
        holder.contentTextview = (TextView) view.findViewById(R.id.textview_content);

//        if(isEnabledDelete == false)
//            holder.checkBox.setVisibility(View.GONE);
//        else
//            holder.checkBox.setVisibility(View.VISIBLE);

//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("---","set checkbox");
//                todo.setSelected(holder.checkBox.isChecked());
//            }
//        });

        int days = ListInGroupAdapter.caluculateDayDiff(todo.getDate());

//        holder.idTextview.setText(String.valueOf(todo.getId()));
        holder.dateTextview.setText(todo.getDate());
        holder.daysTextview.setText(String.valueOf(days));
        holder.titleTextview.setText(todo.getTitle());
//        holder.groupTextview.setText(todo.getGroup());
        holder.contentTextview.setText(todo.getContent());

        return view;
    }
}
