package com.example.yamamotoai.layoutviewcollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by yamamotoai on 2017-07-13.
 */

public class LinearLayout extends AppCompatActivity {

    Button backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        backBtn = (Button) findViewById(R.id.btn);

        backBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LinearLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
