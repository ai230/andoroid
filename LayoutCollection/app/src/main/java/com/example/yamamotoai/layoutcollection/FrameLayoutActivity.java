package com.example.yamamotoai.layoutcollection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by yamamotoai on 2017-07-16.
 */

public class FrameLayoutActivity extends AppCompatActivity {
    Button btnPre;
    Button btnNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        //Create border and set fill color white
        LinearLayout l = (LinearLayout)findViewById(R.id.linear_bg);
        ((GradientDrawable)l.getBackground()).setColor(Color.WHITE);

    }

    public void goToPreviousView(View v) {
        try {
            Intent i = new Intent(FrameLayoutActivity.this, TableLayoutActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }

    }

    public void goToNextView(View v) {
        try {
            Intent i = new Intent(FrameLayoutActivity.this, ListViewActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }
    }
}
