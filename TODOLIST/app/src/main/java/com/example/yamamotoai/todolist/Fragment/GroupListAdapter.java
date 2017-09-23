package com.example.yamamotoai.todolist.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yamamotoai.todolist.Group;
import com.example.yamamotoai.todolist.R;

import java.util.List;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class GroupListAdapter extends BaseAdapter{

    Context context;
    List<Group> list;


    public GroupListAdapter(Context context, List<Group> list){
        this.context = context;
        this.list = list;
    }

    public class Holder{
        TextView groupName, numOfDone, numOfTodo;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_grouplist_row, null);

        Group group = list.get(position);
        holder.groupName = (TextView) rowView.findViewById(R.id.textview_group_row);
        holder.numOfTodo = (TextView) rowView.findViewById(R.id.numOfList);
        holder.numOfDone = (TextView) rowView.findViewById(R.id.numOfDone);

        holder.groupName.setText(group.getGroup());
        holder.numOfTodo.setText(String.valueOf(group.getNumOfTodo()));
        holder.numOfDone.setText(String.valueOf(group.getNumOfDone()));

        return rowView;
    }


}
