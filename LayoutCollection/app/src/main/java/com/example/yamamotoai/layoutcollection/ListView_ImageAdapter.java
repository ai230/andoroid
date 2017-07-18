package com.example.yamamotoai.layoutcollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-18.
 */

public class ListView_ImageAdapter extends BaseAdapter {

    private Context context;
    String[] values;
    Integer[] imageId;

    public ListView_ImageAdapter(Context context, String[] values, Integer[] imageid) {
        this.context = context;
        this.values = values;
        this.imageId = imageid;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        View list;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            list = new View(context);
            list = inflater.inflate(R.layout.gridview_single, null);
            TextView tv = (TextView) list.findViewById(R.id.text1);
            ImageView iv = (ImageView) list.findViewById(R.id.logo);
            tv.setText(values[position]);
            iv.setImageResource(imageId[position]);
        } else {
            list = (View) convertView;
        }
        return list;

//        if (convertView == null) {
//            convertView = getLayoutInflater().inflate(R.layout.gridview_single, container, false);
//        }
//
//        ((TextView) convertView.findViewById(android.R.id.text1))
//                .setText(getItem(position));
//        return convertView;
    }
}
