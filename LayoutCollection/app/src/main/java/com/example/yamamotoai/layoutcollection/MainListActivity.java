package com.example.yamamotoai.layoutcollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by yamamotoai on 2017-07-13.
 */

public class MainListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewformain);

        String[] menuItems = getResources().getStringArray(R.array.menu_items);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.txtviewformain,menuItems);
        ListView listview = (ListView)findViewById(R.id.ListView);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainListActivity.this, LinearLayoutActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainListActivity.this, RelativeLayoutActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainListActivity.this, TableLayoutActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainListActivity.this, FrameLayoutActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainListActivity.this, ListViewActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(MainListActivity.this, GridViewActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(MainListActivity.this, LinearLayoutActivity.class);
                        startActivity(intent6);
                        break;

                    default:
                        break;
                }


                Log.d("---","clicked");

            }
        });
    }
}
