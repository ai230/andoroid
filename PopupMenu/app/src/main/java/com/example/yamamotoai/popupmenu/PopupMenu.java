package com.example.yamamotoai.popupmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-13.
 */

public class PopupMenu extends AppCompatActivity {

    // TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_menu_example);
        //  tv = (TextView)findViewById(R.id.tv_popupmenu);
    }



    public void showPopUpMenu(View v){
        //Creating the instance of PopupMenu
        PopupMenu m= new PopupMenu();
        //Inflating the Popup using xml file
        MenuInflater i = m.getMenuInflater();
        i.inflate(R.menu.menu_main, (Menu) m.getMenuInflater());
//        // This activity implements OnMenuItemClickListener
//        m.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.menu_bookmark:
//                        // Single menu item is selected do something
//                        // Ex: launching new activity/screen or show alert message
//                        Toast.makeText(PopupMenu.this, "Bookmark" +
//                                " is Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_save:
//                        Toast.makeText(PopMenuExample.this, "Save is Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_search:
//                        Toast.makeText(PopMenuExample.this, "Search is Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_share:
//                        Toast.makeText(PopMenuExample.this, "Share is Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_delete:
//                        Toast.makeText(PopMenuExample.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_preferences:
//                        Toast.makeText(PopMenuExample.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });



        m.show();//showing popup menu
    }




}
