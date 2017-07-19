package com.example.yamamotoai.layoutcollection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-14.
 */

public class RelativeLayoutActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);

        //Create border and set fill color white
        LinearLayout l = (LinearLayout)findViewById(R.id.linear_bg);
        ((GradientDrawable)l.getBackground()).setColor(Color.WHITE);

        //to justify this textview
        WebView view = new WebView(this);
        view.setVerticalScrollBarEnabled(false);
        ((LinearLayout)findViewById(R.id.lv_justified)).addView(view);
        view.loadData(getString(R.string.relativelayout), "text/html; charset=utf-8", "utf-8");

    }

    public void goToPreviousView(View v) {
        try {
            Intent i = new Intent(RelativeLayoutActivity.this, LinearLayoutActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }
    }

    public void goToNextView(View v) {
        try {
            Intent i = new Intent(RelativeLayoutActivity.this, TableLayoutActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }
    }
}
