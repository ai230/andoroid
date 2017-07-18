package com.example.yamamotoai.layoutcollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-17.
 */

public class GridView_ImageAdapter extends BaseAdapter {

    //constructor
    private Context c;
    String[] values;
    Integer[] imageId;

    public GridView_ImageAdapter(Context c, String[] values, Integer[] imageid) {
        this.c = c;
        this.values = values;
        this.imageId = imageid;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Create a new imageview for the item referenced by adapter
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(c);
            grid = inflater.inflate(R.layout.gridview_single, null);
            TextView tv = (TextView) grid.findViewById(R.id.text1);
            ImageView iv = (ImageView) grid.findViewById(R.id.logo);
            tv.setText(values[position]);
            iv.setImageResource(imageId[position]);
            grid.setPadding(8, 8, 8, 8);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }

}
