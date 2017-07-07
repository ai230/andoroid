package com.example.yamamotoai.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-06.
 */

public class IntentexampleA extends AppCompatActivity{
    //1. declare all the controls
    TextView t1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        //2. bind all the controls

        t1 = (TextView) findViewById(R.id.txtA);
        b1 = (Button) findViewById(R.id.btnA);

        //3. define the onclicklistner event
        b1.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntentexampleA.this,IntentexampleB.class);
                startActivity(i);
            }
        });


    }
}
