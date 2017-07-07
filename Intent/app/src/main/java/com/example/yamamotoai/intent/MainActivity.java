package com.example.yamamotoai.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b;
    Button b1;
    Button b2;
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String msg = "This is send message";

        e = (EditText) findViewById(R.id.editTxt);
        b = (Button) findViewById(R.id.btn);
        b1 = (Button) findViewById(R.id.btnOpenWeb);
        b2 = (Button) findViewById(R.id.btnSendEmail);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("msg", e.getText().toString());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("-----","google");
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ca"));
                startActivity(i1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("-----","email");
                Intent i2 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","abc@gmail.com",null));
                i2.putExtra(Intent.EXTRA_SUBJECT,"subject");
                i2.putExtra(Intent.EXTRA_TEXT,"Body");
                Log.d("-----","email------");
                startActivity(Intent.createChooser(i2,""));
            }
        });
    }
}