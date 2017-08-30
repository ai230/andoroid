package com.example.yamamotoai.myapplication;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by yamamotoai on 2017-08-27.
 */

public class SearchResultsActivity extends AppCompatActivity {

    ListView listView1;
    ArrayList<String> myArray1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        myArray1 = new ArrayList<>();

        listView1 = (ListView) findViewById(R.id.listview1);
        listView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, myArray1));

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        handleIntent(intent);

    }

    private void handleIntent(Intent intent) {

        Log.d("--",Intent.ACTION_SEARCH);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    public void doMySearch(String query){

        Log.d("","");

    }
}
