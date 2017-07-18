package com.example.yamamotoai.recyclerviewexample2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-07-18.
 */

public class MainMovieActivity extends AppCompatActivity{

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewex);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }

    public void prepareMovieData() {
        Movie movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new Movie("Passengers", "Action & Adventure", "2016");
        movieList.add(movie);
        movie = new Movie("Inception", "Action", "2010");
        movieList.add(movie);
        movie = new Movie("Titanic", "Romance", "1997");
        movieList.add(movie);
        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new Movie("Passengers", "Action & Adventure", "2016");
        movieList.add(movie);
        movie = new Movie("Inception", "Action", "2010");
        movieList.add(movie);
        movie = new Movie("Titanic", "Romance", "1997");
        movieList.add(movie);
        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new Movie("Passengers", "Action & Adventure", "2016");
        movieList.add(movie);
        movie = new Movie("Inception", "Action", "2010");
        movieList.add(movie);
        movie = new Movie("Titanic", "Romance", "1997");
        movieList.add(movie);
    }
}
