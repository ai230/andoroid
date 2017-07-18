//package com.example.yamamotoai.layoutviewcollection;
//
//import android.content.Context;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
///**
// * Created by yamamotoai on 2017-07-12.
// */
//
//public class MainArrayAdapter extends ArrayAdapter<String> {
//
//    private final Context context;
//    private final String[] menuItems;
//
//
//    public MainArrayAdapter(Context context, String[] menuItems) {
//        super(context, R.layout.activity_main);
//        this.context = context;
//        this.menuItems = menuItems;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.activity_main);
//        TextView textView = (TextView) rowView.findViewById(R.id.TextView);
//
//
//
//        return super.getView(position, convertView, parent);
//    }
//}
