package com.example.yamamotoai.gridviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by yamamotoai on 2017-07-12.
 */

public class ImageAdapter2 extends BaseAdapter {
    //constructor
    private Context c;
    String[] values;
    Integer[] imageId;

    public ImageAdapter2(Context c, String[] values, Integer[] imageid) {
        this.c = c;
        this.values = values;
        this.imageId = imageid;
    }



    //number of items of your collectionview
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
//        ImageView iv;
//        TextView tv;
        if (convertView == null) {
            grid = new View(c);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView tv = (TextView) grid.findViewById(R.id.label);
            ImageView iv = (ImageView) grid.findViewById(R.id.logo);
            tv.setText(values[position]);
            iv.setImageResource(imageId[position]);
//            iv.setLayoutParams(new GridView.LayoutParams(200, 200));
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            grid.setPadding(8, 8, 8, 8);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
