package com.example.yamamotoai.layoutcollection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-07-18.
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private List<RecyclerViewMovie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewex);

        //Create border and set fill color white
        LinearLayout l = (LinearLayout)findViewById(R.id.linear_bg);
        ((GradientDrawable)l.getBackground()).setColor(Color.WHITE);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mAdapter = new RecyclerViewAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }

    public void prepareMovieData() {
        RecyclerViewMovie movie = new RecyclerViewMovie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Passengers", "Action & Adventure", "2016");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Inception", "Action", "2010");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Titanic", "Romance", "1997");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Passengers", "Action & Adventure", "2016");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Inception", "Action", "2010");
        movieList.add(movie);
        movie = new RecyclerViewMovie("Titanic", "Romance", "1997");
        movieList.add(movie);
    }

    public void goToPreviousView(View v) {
        try {
            Intent i = new Intent(RecyclerViewActivity.this, GridViewActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }

    }

    public void goToNextView(View v) {
        try {
            Intent i = new Intent(RecyclerViewActivity.this, MainListActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.d("---", "error");
        }
    }
}
