package com.example.yamamotoai.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class SecondActivityAdapter extends BaseAdapter{

    private List<TODO> todoListInGroup;
    private List<TODO> todoList;
    private Context context;
    boolean isEnabledDelete;

    public SecondActivityAdapter(Context context, List<TODO> list, boolean isEnabledDelete){
        this.context = context;
        this.todoListInGroup = list;
        this.isEnabledDelete = isEnabledDelete;
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
        return todoListInGroup.size();
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
        final TODO todo = todoListInGroup.get(position);
        View view =  convertView;
        final Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, null);
        holder.checkBox = (CheckBox) rowView.findViewById(R.id.checkbox_second);
        holder.idTextview = (TextView) rowView.findViewById(R.id.textview_id);
        holder.dateTextview = (TextView) rowView.findViewById(R.id.textview_date);
        holder.daysTextview = (TextView) rowView.findViewById(R.id.textview_days);
        holder.titleTextview = (TextView) rowView.findViewById(R.id.textview_title);
        holder.groupTextview = (TextView) rowView.findViewById(R.id.textview_group);
        holder.contentTextview = (TextView) rowView.findViewById(R.id.textview_content);

        if(isEnabledDelete == false)
            holder.checkBox.setVisibility(View.GONE);
        else
            holder.checkBox.setVisibility(View.VISIBLE);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("---","set checkbox");
                todo.setSelected(holder.checkBox.isChecked());
            }
        });

        int days = caluculateDayDiff(todo.getDate());

        holder.idTextview.setText(String.valueOf(todo.getId()));
        holder.dateTextview.setText(todo.getDate());
        holder.daysTextview.setText(String.valueOf(days));
        holder.titleTextview.setText(todo.getTitle());
        holder.groupTextview.setText(todo.getGroup());
        holder.contentTextview.setText(todo.getContent());

        return rowView;
    }

    public int caluculateDayDiff(String date){
        String expression = date;
        String[] tokens = expression.split("-");
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);

        Calendar current = Calendar.getInstance();
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.set(year,month-1,day);

        long msDiff = calendar.getTimeInMillis() - current.getTimeInMillis();
        long days = (int) TimeUnit.MILLISECONDS.toDays(msDiff);

        return (int) days;
    }
}
