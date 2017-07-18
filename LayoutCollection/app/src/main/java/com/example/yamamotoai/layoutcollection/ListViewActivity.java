package com.example.yamamotoai.layoutcollection;

import android.app.ListActivity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-16.
 */

public class ListViewActivity extends AppCompatActivity {
    ListView lv;
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
        setContentView(R.layout.listview);

        //Create border and set fill color white
        LinearLayout l = (LinearLayout)findViewById(R.id.linear_bg);
        ((GradientDrawable)l.getBackground()).setColor(Color.WHITE);

        lv = (ListView)findViewById(R.id.listview);

        String[] items = getResources().getStringArray(R.array.fruits);
        ArrayAdapter a = new ArrayAdapter<String>(this,R.layout.listview_textview,items);
//        ListView l = (ListView)findViewById(R.id.ListView_menu);
        lv.setAdapter(a);
        registerForContextMenu(lv);

//        lv = (ListView) findViewById(R.id.listview);
//        lv.setAdapter(new ListView_ImageAdapter(this,FRUITS,fruits_img));
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Context context = getApplicationContext();
//                CharSequence text = "Position: " + i + " Clicked!!";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//        });
    }

    public void goToPreviousView(View v) {
        try {
            Intent i = new Intent(ListViewActivity.this, FrameLayoutActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }

    }

    public void goToNextView(View v) {
        try {
            Intent i = new Intent(ListViewActivity.this, GridViewActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }
    }

}
