package com.example.yamamotoai.todolist.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yamamotoai.todolist.R;
import com.example.yamamotoai.todolist.TODO;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class ListInGroupAdapter extends BaseAdapter{

    private List<TODO> todoListInGroup;
    private List<TODO> todoList;
    private Context context;
    public static boolean isEnabledDelete;

    public ListInGroupAdapter(Context context, List<TODO> list, boolean isEnabledDelete){
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
        LinearLayout linearLayout;
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
        View rowView = inflater.inflate(R.layout.fragment_todolist_row, null);
        holder.checkBox = (CheckBox) rowView.findViewById(R.id.checkbox_select);
        holder.dateTextview = (TextView) rowView.findViewById(R.id.textview_date);
        holder.daysTextview = (TextView) rowView.findViewById(R.id.textview_days);
        holder.titleTextview = (TextView) rowView.findViewById(R.id.textview_title);
        holder.contentTextview = (TextView) rowView.findViewById(R.id.textview_content);
        holder.linearLayout = (LinearLayout) rowView.findViewById(R.id.linealayout1);

        if(todo.isDone()) {
            holder.linearLayout.setBackgroundColor(Color.LTGRAY);
            holder.dateTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.titleTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.contentTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
            holder.daysTextview.setTextColor(context.getResources().getColor(R.color.colorGray));
        }
        if(isEnabledDelete == false) {
            holder.checkBox.setVisibility(View.GONE);
            todo.setSelected(false);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(todo.isSelected());
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.setSelected(holder.checkBox.isChecked());
            }
        });

        if(todo.getDate() != null){
            int days = caluculateDayDiff(todo.getDate());

            holder.dateTextview.setText(todo.getDate());
            holder.daysTextview.setText(String.valueOf(days));
        }

        holder.titleTextview.setText(todo.getTitle());
        holder.contentTextview.setText(todo.getContent());

        return rowView;
    }

    public static int caluculateDayDiff(String date){
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
