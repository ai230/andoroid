package com.example.yamamotoai.movielist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

//6.implement adapter.ListItemClickListener
public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{

    private static MainActivity instance = null;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        instance = this;
//        checkBoxToggle();
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        //8.add this to the listnener
        mAdapter = new MovieAdapter(movieList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }
    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void prepareMovieData() {

        Movie movie = new Movie("Avater", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "2009", "avatar");
        movieList.add(movie);
        movie = new Movie("The Hunger Games", "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games: a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.", "2012", "theungergames");
        movieList.add(movie);
        movie = new Movie("Divergent", "In a world divided by factions based on virtues, Tris learns she's Divergent and won't fit in. When she discovers a plot to destroy Divergents, Tris and the mysterious Four must find out what makes Divergents dangerous before it's too late.", "2014", "divergent");
        movieList.add(movie);
        movie = new Movie("Léon: The Professional", "Mathilda, a 12-year-old girl, is reluctantly taken in by Léon, a professional assassin, after her family is murdered. Léon and Mathilda form an unusual relationship, as she becomes his protégée and learns the assassin's trade.", "1994", "leon");
        movieList.add(movie);
        movie = new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", "1997", "titanic");
        movieList.add(movie);
        movie = new Movie("The Intouchables", "After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.", "2011", "theungergames");
        movieList.add(movie);
        movie = new Movie("Avater", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "2009", "avatar");
        movieList.add(movie);
        movie = new Movie("The Hunger Games", "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games: a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.", "2012", "theungergames");
        movieList.add(movie);
        movie = new Movie("Divergent", "In a world divided by factions based on virtues, Tris learns she's Divergent and won't fit in. When she discovers a plot to destroy Divergents, Tris and the mysterious Four must find out what makes Divergents dangerous before it's too late.", "2014", "divergent");
        movieList.add(movie);
        movie = new Movie("Léon: The Professional", "Mathilda, a 12-year-old girl, is reluctantly taken in by Léon, a professional assassin, after her family is murdered. Léon and Mathilda form an unusual relationship, as she becomes his protégée and learns the assassin's trade.", "1994", "leon");
        movieList.add(movie);
        movie = new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", "1997", "titanic");
        movieList.add(movie);
        movie = new Movie("The Intouchables", "After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.", "2011", "theungergames");
        movieList.add(movie);
        movie = new Movie("Avater", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "2009", "avatar");
        movieList.add(movie);
        movie = new Movie("The Hunger Games", "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games: a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.", "2012", "theungergames");
        movieList.add(movie);
        movie = new Movie("Divergent", "In a world divided by factions based on virtues, Tris learns she's Divergent and won't fit in. When she discovers a plot to destroy Divergents, Tris and the mysterious Four must find out what makes Divergents dangerous before it's too late.", "2014", "divergent");
        movieList.add(movie);
        movie = new Movie("Léon: The Professional", "Mathilda, a 12-year-old girl, is reluctantly taken in by Léon, a professional assassin, after her family is murdered. Léon and Mathilda form an unusual relationship, as she becomes his protégée and learns the assassin's trade.", "1994", "leon");
        movieList.add(movie);
        movie = new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", "1997", "titanic");
        movieList.add(movie);
        movie = new Movie("The Intouchables", "After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.", "2011", "theungergames");
        movieList.add(movie);
        movie = new Movie("Avater", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "2009", "avatar");
        movieList.add(movie);
        movie = new Movie("The Hunger Games", "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games: a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.", "2012", "theungergames");
        movieList.add(movie);
        movie = new Movie("Divergent", "In a world divided by factions based on virtues, Tris learns she's Divergent and won't fit in. When she discovers a plot to destroy Divergents, Tris and the mysterious Four must find out what makes Divergents dangerous before it's too late.", "2014", "divergent");
        movieList.add(movie);
        movie = new Movie("Léon: The Professional", "Mathilda, a 12-year-old girl, is reluctantly taken in by Léon, a professional assassin, after her family is murdered. Léon and Mathilda form an unusual relationship, as she becomes his protégée and learns the assassin's trade.", "1994", "leon");
        movieList.add(movie);
        movie = new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", "1997", "titanic");
        movieList.add(movie);
        movie = new Movie("The Intouchables", "After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.", "2011", "theungergames");
        movieList.add(movie);
    }

    public void didClearBtnClicked(View v) {
        Log.d("---","clicked1");


        mAdapter = new MovieAdapter(movieList,this);
        recyclerView.setAdapter(mAdapter);

    }

    public void didSelectBtnClicked(View v) {
        for(Movie m:movieList){
        mAdapter.checkBoxToggle(v);}
        Log.d("---","clicked2");
    }

    public void didDeleteBtnClicked(View v) {
        Log.d("---","clicked3");
    }

    //7.implement method
    @Override
    public void onListItemClick(int position) {
        Log.d("---","clicked");
        Toast.makeText(this,"Item # " + position + " is clicked",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, DetailsOfMovieActivity.class);
        Movie m = movieList.get(position);
        String mTitle = m.getTitle();
        String mDetail = m.getDetail();
        String mYear = m.getYear();
        String mImg = m.getImg();
        intent.putExtra("mTitle",mTitle);
        intent.putExtra("mDetail",mDetail);
        intent.putExtra("mYear",mYear);
        intent.putExtra("mImg",mImg);
        startActivity(intent);
    }
}
