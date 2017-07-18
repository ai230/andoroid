package com.example.yamamotoai.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-13.
 */

public class PopupMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_menu);
    }

    public void showPopup(View v) {
        android.widget.PopupMenu p = new android.widget.PopupMenu(getApplicationContext(), v);
        MenuInflater inflater = getMenuInflater();
        p.getMenuInflater().inflate(R.menu.menu_popup, p.getMenu());
        p.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        Toast.makeText(PopupMenu.this, "One" +
                                " is Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.two:
                        Toast.makeText(PopupMenu.this, "Two" +
                                " is Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.three:
                        Toast.makeText(PopupMenu.this, "Three" +
                                " is Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        p.show();
    }

}



