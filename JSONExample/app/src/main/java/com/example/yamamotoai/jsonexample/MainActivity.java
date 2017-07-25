package com.example.yamamotoai.jsonexample;

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
//import java.util.StringJoiner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity{

//    private static MainActivity instance = null;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private List<Boolean> isSelectedArray;
    //json
    private static String TAG = MainActivity.class.getSimpleName();
    //1. json array response url
    private String urlJsonArry = "http://192.168.56.1/moviedata.json";
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

//        instance = this;

        recyclerView = (RecyclerView) findViewById(R.id.rv_movie);
        //8.add this to the listener
        mAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        makeJsonArrayRequest();
        Log.d("---","ok4");
    }
    /**
     * Method to make json array request where response starts with [
     */
    private void makeJsonArrayRequest() {
        Log.d("---","ok");
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse (JSONArray response) {
                        Log.d(TAG, response.toString());
                        Log.d("---","ok1");
                        try {
                            Log.d("---","ok2");
                            Movie moviedata = null;
                            for (int i = 0; i < response.length(); i++) {
                                moviedata = new Movie();
                                JSONObject movie = (JSONObject) response.get(i);
                                String title = movie.getString("title");
                                String genre = movie.getString("genre");
                                String year = movie.getString("year");
                                String cast = movie.getString("cast");
                                moviedata.setTitle(title);
                                moviedata.setGenre(genre);
                                moviedata.setYear(year);
                                moviedata.setCast(cast);
                                movieList.add(moviedata);
                            }
                            mAdapter = new MovieAdapter(movieList);
                            recyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("---","ok3");
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("---", String.valueOf(req));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
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

    public void didClearBtnClicked(View v) {
        Log.d("---","clicked1");
        for(Movie m: movieList){
            m.setSelected(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void didSelectBtnClicked(View v) {
        for(Movie m: movieList){
            m.setSelected(true);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void didDeleteBtnClicked(View v) {
        int arraySize = movieList.size();
        for(int i=0; i<arraySize; i++){
            if( movieList.get(i).isSelected() == true){
                movieList.remove(i);
                i -= 1;
                arraySize -= 1;
                Log.d("---","---");
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    //7.implement method
//    @Override
//    public void onListItemClick(int position) {
//        Toast.makeText(this,"Item # " + position + " is clicked",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this, DetailsOfMovieActivity.class);
//        Movie m = movieList.get(position);
//        String mTitle = m.getTitle();
//        String mDetail = m.getDetail();
//        String mYear = m.getYear();
//        String mImg = m.getImg();
//        intent.putExtra("mTitle",mTitle);
//        intent.putExtra("mDetail",mDetail);
//        intent.putExtra("mYear",mYear);
//        intent.putExtra("mImg",mImg);
//        startActivity(intent);
//    }
}
