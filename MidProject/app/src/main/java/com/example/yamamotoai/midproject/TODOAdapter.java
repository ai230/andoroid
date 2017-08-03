package com.example.yamamotoai.midproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

        //date, title, group,content;
        TextView dateTextView;
        TextView titleTextView;
        TextView groupTextView;
        TextView contentTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.textview_date);
            titleTextView = (TextView) itemView.findViewById(R.id.textview_title);
            groupTextView = (TextView) itemView.findViewById(R.id.textview_group);
            contentTextView = (TextView) itemView.findViewById(R.id.textview_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickListener.onListItemClick(position);
            Log.d("---","onClick: " + position);
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
//        View view = inflater.inflate(R.layout.list_row, parent, false);
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
    }

    @Override
    public int getItemCount() {
        Log.d("---adapter", String.valueOf(todo_List.size()));
        return todo_List.size();
    }

}
