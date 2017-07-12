package com.example.yamamotoai.scrollviewexample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-11.
 */

public class ActivityScrollView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        String[] menuItems = getResources().getStringArray(R.array.menu_items);
        ArrayAdapter a = new ArrayAdapter<String>(this,R.layout.activity_textview,menuItems);
        ListView l = (ListView)findViewById(R.id.ListView_menu);
        l.setAdapter(a);
//        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String itemValue = (String)l.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), "Position" + position + "Your...", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
