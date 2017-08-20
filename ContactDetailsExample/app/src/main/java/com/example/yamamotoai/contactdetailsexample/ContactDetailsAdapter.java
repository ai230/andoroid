package com.example.yamamotoai.contactdetailsexample;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yamamotoai on 2017-08-19.
 */

public class ContactDetailsAdapter extends BaseAdapter{

    Context context;
    ArrayList<Contact> arrayList;

    public ContactDetailsAdapter(Context context, ArrayList<Contact> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public class Holder{

        TextView nameTextView;
        TextView numberTextView;

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Contact contact = arrayList.get(position);
        Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, null);
        holder.nameTextView = (TextView) rowView.findViewById(R.id.text_name);
        holder.numberTextView = (TextView) rowView.findViewById(R.id.text_number);
        holder.nameTextView.setText(contact.getName());
        holder.numberTextView.setText(contact.getNumber());
        return rowView;
    }
}
