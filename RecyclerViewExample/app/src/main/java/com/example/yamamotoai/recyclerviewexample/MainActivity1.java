package com.example.yamamotoai.recyclerviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-17.
 */

//e.implement GreenAdapter.ListItemClickListener
public class MainActivity1 extends AppCompatActivity implements GreenAdapter.ListItemClickListener{
    //having 100 different items
    private static final int NUM_LIST_COUNT = 100;
    private GreenAdapter greenAdapter;
    private RecyclerView rv;
//    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_main);

        rv = (RecyclerView) findViewById(R.id.rv1);
        LinearLayoutManager lv = new LinearLayoutManager(this);
        //conect RecyclerView and LinearLayout
        rv.setLayoutManager(lv);
        rv.setHasFixedSize(true);
        //g.add one more parameter(this)
        greenAdapter = new GreenAdapter(NUM_LIST_COUNT,this);
        rv.setAdapter(greenAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_refresh:
                //g.add one more parameter(this)
                greenAdapter = new GreenAdapter(NUM_LIST_COUNT,this);
                rv.setAdapter(greenAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //f.implement method
    @Override
    public void onListItemClick(int position) {
        Toast.makeText(this,"Item # " + position + " is clicked",Toast.LENGTH_SHORT).show();
    }
}
