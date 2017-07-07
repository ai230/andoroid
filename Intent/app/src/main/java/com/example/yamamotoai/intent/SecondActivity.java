package com.example.yamamotoai.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-06.
 */

public class SecondActivity extends AppCompatActivity {

    Button btn;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = (Button) findViewById(R.id.btn);
        txtView = (TextView) findViewById(R.id.txt);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("msg");
        txtView.setText(message);

        //3. define the onclicklistner event
        btn.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
