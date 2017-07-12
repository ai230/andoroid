package com.example.yamamotoai.gridviewexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by yamamotoai on 2017-07-12.
 */

public class ImageAdapter extends BaseAdapter
{
    //constructor
    private Context c;

    public  ImageAdapter(Context c) {
        this.c = c;
    }

    //Create an array for the images
    private Integer[] imageArray = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7
    };

    //number of items of your collectionview
    @Override
    public int getCount() {
        return imageArray.length;
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
        ImageView iv;

        if (convertView == null) {
            iv = new ImageView(c);
            iv.setLayoutParams(new GridView.LayoutParams(200, 200));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        } else {
            iv = (ImageView) convertView;
        }
        iv.setImageResource(imageArray[position]);
        return iv;
    }

}
