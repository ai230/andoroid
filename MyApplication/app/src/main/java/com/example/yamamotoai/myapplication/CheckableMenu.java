package com.example.yamamotoai.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-13.
 */

public class CheckableMenu extends AppCompatActivity {
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkable_menu);

        btn = (Button) findViewById(R.id.btn_checkable);
        txt = (TextView) findViewById(R.id.txt_checkable);

        registerForContextMenu(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("---","clicked");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkable, menu);
//        SharedPreferences settings = getSharedPreferences("settings", 0);
//        boolean isChecked = settings.getBoolean("checkbox", false);
//        MenuItem item = menu.findItem(R.id.red);
//        item.setChecked(isChecked);

        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_checkable,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action1_sub1:
//                Log.d("---",item.toString());
//            txt.setText(item.toString());
//            case R.id.action1_sub2:
//            case R.id.action1_sub3:
//                if (item.isChecked()) item.setChecked(false);
//                else item.setChecked(true);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.one:
            case R.id.two:
            case R.id.three:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
