package com.example.yamamotoai.layoutcollection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-16.
 */

public class GridViewActivity extends AppCompatActivity {
    GridView gv;
//    private String[] fruits = getResources().getStringArray(R.array.fruits);

    //Create an array for the images
    private Integer[] fruits_img = {
            R.drawable.apple,
            R.drawable.avocado,
            R.drawable.banana,
            R.drawable.blueberry,
            R.drawable.mango
    };


    static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
            "Blueberry","Mango"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);

        //Create border and set fill color white
        LinearLayout l = (LinearLayout)findViewById(R.id.linear_bg);
        ((GradientDrawable)l.getBackground()).setColor(Color.WHITE);

        gv = (GridView)findViewById(R.id.gridview);
        gv.setAdapter(new GridView_ImageAdapter(this,FRUITS,fruits_img));
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

    public void goToPreviousView(View v) {
        try {
            Intent i = new Intent(GridViewActivity.this, ListViewActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }

    }

    public void goToNextView(View v) {
        try {
            Intent i = new Intent(GridViewActivity.this, MainListActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }
    }
}

