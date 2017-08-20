package com.example.yamamotoai.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class MainActivityAdapter extends BaseAdapter{

    Context context;
    List<String> list;

    public MainActivityAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    public class Holder{
        ImageView imageView;
        TextView textView;
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
        TODO todo = list.get(position);
        View view = convertView;
        final Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_list_row, null);

        holder.imageView = (ImageView) rowView.findViewById(R.id.imageview_group_row);
        holder.textView = (TextView) rowView.findViewById(R.id.textview_group_row);

        holder.textView.setText(list.get(position));
        return rowView;
    }


}
