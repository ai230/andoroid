package com.example.yamamotoai.scrollviewexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-11.
 */

public class MobileArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    //Create a constructor
    public MobileArrayAdapter(Context context, String[] values) {
        super(context, R.layout.listviewwithimage, values);
        this.context = context;
        this.values = values;
    }
    //CALL A SERVICE TO CREATE A View DYNAMICALLY

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("---","ok");
        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        Log.d("---","ok1");
        View rowView = inflater.inflate(R.layout.listviewwithimage,null);
        Log.d("---","ok2");
                //l.inflate(R.layout.listviewwithimage,parent);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);
        //used to change icon based on Mobile OS
        String s = values[position];
        if(s.equals("Android"))
            imageView.setImageResource(R.drawable.android);
        else if(s.equals("iOS"))
            imageView.setImageResource(R.drawable.ios);
        else if (s.equals("Blackberry"))
            imageView.setImageResource(R.drawable.blackberry);
        else
            imageView.setImageResource(R.drawable.android);

        return rowView;

    }

}
