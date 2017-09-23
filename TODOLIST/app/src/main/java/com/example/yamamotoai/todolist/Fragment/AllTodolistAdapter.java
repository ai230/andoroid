package com.example.yamamotoai.todolist.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yamamotoai.todolist.R;
import com.example.yamamotoai.todolist.TODO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-09-07.
 */

public class AllTodolistAdapter extends BaseAdapter{

    List<TODO> list = new ArrayList<>();
    Context context;

    public AllTodolistAdapter(Context context, List<TODO> list) {
        this.list = list;
        this.context = context;
    }

    public class Holder{
        CheckBox checkBox;
        TextView idTextview;
        TextView dateTextview;
        TextView daysTextview;
        TextView titleTextview;
        TextView groupTextview;
        TextView contentTextview;
        LinearLayout linearLayout;
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
    public View getView(int position, View covertView, ViewGroup viewGroup) {
        TODO todo = list.get(position);
        Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_all_todolist_row, null);
        holder.checkBox = (CheckBox) view.findViewById(R.id.checkbox1);
        holder.dateTextview = (TextView) view.findViewById(R.id.textview_date1);
        holder.daysTextview = (TextView) view.findViewById(R.id.textview_days1);
        holder.titleTextview = (TextView) view.findViewById(R.id.textview_title1);
        holder.groupTextview = (TextView) view.findViewById(R.id.textview_group1);
        holder.contentTextview = (TextView) view.findViewById(R.id.textview_content1);
        holder.linearLayout = (LinearLayout) view.findViewById(R.id.linealayout1);

        int days = ListInGroupAdapter.caluculateDayDiff(todo.getDate());

        holder.dateTextview.setText(todo.getDate());
        holder.daysTextview.setText(String.valueOf(days));
        holder.titleTextview.setText(todo.getTitle());
        holder.groupTextview.setText(todo.getGroup());
        holder.contentTextview.setText(todo.getContent());
        if(todo.isDone()) {
            holder.linearLayout.setBackgroundColor(Color.LTGRAY);
            holder.dateTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.titleTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.groupTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.contentTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.daysTextview.setTextColor(context.getResources().getColor(R.color.colorGray));

        }
        return view;
    }
}
