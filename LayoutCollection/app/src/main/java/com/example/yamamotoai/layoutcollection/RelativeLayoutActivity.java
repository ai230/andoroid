package com.example.yamamotoai.layoutcollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by yamamotoai on 2017-07-14.
 */

public class RelativeLayoutActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
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
