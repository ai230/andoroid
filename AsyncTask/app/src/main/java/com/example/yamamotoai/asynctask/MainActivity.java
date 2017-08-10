package com.example.yamamotoai.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String[] cities = {"KAGAWA","KOCHI","EHIME","TOKUSHIMA","HIROSHIMA","OKAYAMA","TOTTORI","SHIMANE","HIROSHIMA","KAGAWA","KOCHI","EHIME","TOKUSHIMA","HIROSHIMA","OKAYAMA","TOTTORI","SHIMANE","HIROSHIMA"};

    ListView list_Cities;
    ProgressBar progress_cities;
    TextView text_Cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_Cities = (ListView) findViewById(R.id.listviewprogress);
        progress_cities = (ProgressBar) findViewById(R.id.progresslistitems);
        text_Cities = (TextView) findViewById(R.id.textviewprogress);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, new ArrayList<String>());
        list_Cities.setAdapter(arrayAdapter);
        new MyTask().execute();

    }

    class MyTask extends AsyncTask<Void,String,Void>{
        ArrayAdapter<String> adapter;
        int count;
        int val_progress;
        //TODO)1 onPreExecute
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) list_Cities.getAdapter();
            //when it starts it will be displayed
            progress_cities.setVisibility(View.VISIBLE);
        }
        //TODO)2 doInBackground
        @Override
        protected Void doInBackground(Void... voids) {
            for(String item: cities){
                //passing to onProgressUpdate() String of city
                publishProgress(item);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        //TODO)3 onProgressUpdate
        @Override
        protected void onProgressUpdate(String... values) {
            //getting String of city
            adapter.add(values[0]);
            count++;
            // calculate the percentage
            val_progress = (int)((double) count/cities.length * 100);
            progress_cities.setProgress(val_progress);
            Log.d("--count", String.valueOf(count));
            Log.d("--cities.length", String.valueOf(cities.length));
            Log.d("--val_progress", String.valueOf(val_progress));
            text_Cities.setText("Updating..." + val_progress + "%");
        }
        //TODO)4 onPostExecute
        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this,"ALL ITEMS ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            progress_cities.setVisibility(View.GONE);
        }

    }
}
