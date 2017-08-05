package com.example.yamamotoai.midproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class TODOAdapter extends RecyclerView.Adapter<TODOAdapter.MyViewHolder> {

    private List<TODO> todo_List;
    Context context;

    final private ListItemClickListener onClickListener;
    public  interface ListItemClickListener {
        void onListItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView dateTextView;
        TextView titleTextView;
        TextView groupTextView;
        TextView contentTextView;
        TextView daysTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            dateTextView = (TextView) itemView.findViewById(R.id.textview_date);
            titleTextView = (TextView) itemView.findViewById(R.id.textview_title);
            groupTextView = (TextView) itemView.findViewById(R.id.textview_group);
            contentTextView = (TextView) itemView.findViewById(R.id.textview_content);
            daysTextView = (TextView) itemView.findViewById(R.id.textview_days);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickListener.onListItemClick(position);
        }
    }

    public TODOAdapter(List<TODO> todoList,ListItemClickListener listener){
        this.todo_List = todoList;
        this.onClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TODO t = todo_List.get(position);
        holder.dateTextView.setText(t.getDate());
        holder.titleTextView.setText(t.getTitle());
        holder.contentTextView.setText(t.getContent());

        int daysDiff = caluculateDayDiff(t.getDate());
        holder.daysTextView.setText(String.valueOf(daysDiff));
        if(daysDiff < 4){
            holder.daysTextView.setBackgroundResource(R.drawable.day_background1);
        }
    }

    @Override
    public int getItemCount() {
        return todo_List.size();
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
        long days = (int)TimeUnit.MILLISECONDS.toDays(msDiff);

        return (int) days;
    }

}
