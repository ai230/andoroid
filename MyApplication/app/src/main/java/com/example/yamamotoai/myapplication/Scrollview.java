package com.example.yamamotoai.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by yamamotoai on 2017-07-13.
 */

public class Scrollview extends AppCompatActivity{
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lv = (ListView)findViewById(R.id.ListView_menu);

        String[] menuItems = getResources().getStringArray(R.array.menu_items);
        ArrayAdapter a = new ArrayAdapter<String>(this,R.layout.activity_textview,menuItems);
        ListView l = (ListView)findViewById(R.id.ListView_menu);
        l.setAdapter(a);
        registerForContextMenu(lv);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
