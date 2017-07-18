package com.example.yamamotoai.gridviewexample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-12.
 */

public class HelloGridView2 extends AppCompatActivity{
    GridView gv;

    //Create an array for the images
    private Integer[] imageArray = {
            R.drawable.android,
            R.drawable.blackberry,
            R.drawable.ios,
            R.drawable.windows,
            R.drawable.android,
            R.drawable.blackberry,
            R.drawable.ios,
            R.drawable.windows
    };

    private String[] textArray = new String[] { "Android", "Blackberry",
            "iOS", "WindowsMobile","Android", "Blackberry",
            "iOS", "WindowsMobile"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_example);
        gv = (GridView)findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter2(this,textArray,imageArray));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                CharSequence text = "Position: " + i + " Clicked!!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
