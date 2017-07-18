package com.example.yamamotoai.layoutviewcollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] menuItems = getResources().getStringArray(R.array.menu_items);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.txtview,menuItems);
        ListView listview = (ListView)findViewById(R.id.ListView);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, LinearLayout.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, RelativeLayout.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }


                Log.d("---","clicked");

            }
        });
    }
}
