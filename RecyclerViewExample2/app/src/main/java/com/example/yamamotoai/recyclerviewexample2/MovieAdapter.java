package com.example.yamamotoai.recyclerviewexample2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-07-18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private int count = 0;
    private List<Movie> movie_list;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView t1;
        TextView t2;
        TextView t3;
        MyViewHolder(View v){
            super(v);
            t1 = (TextView) v.findViewById(R.id.tv1);
            t2 = (TextView) v.findViewById(R.id.tv2);
            t3 = (TextView) v.findViewById(R.id.tv3);
            //setClickListener
//            v.setOnClickListener((View.OnClickListener) this);
        count++;
        }

    }

    public MovieAdapter(List<Movie> movie_list){
        this.movie_list = movie_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        //create a object of LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(context);
        //create layout for textview
        View view = inflater.inflate(R.layout.movie_list_row,parent,false);
        //need to pass this view to NumberViewHolder
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movie_list.get(position);

        holder.t1.setText(movie.getTitle());
        holder.t2.setText(movie.getGenre());
        holder.t3.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }
}
