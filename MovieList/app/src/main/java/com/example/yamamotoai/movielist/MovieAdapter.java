package com.example.yamamotoai.movielist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yamamotoai on 2017-07-19.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<Movie> movie_list;
    private static int viewHolderCount;//total number of viewholder

    int lastPosition = -1;
    //1.
    final private ListItemClickListener onClickListener;
    //2.
    public interface ListItemClickListener {
        void onListItemClick(int index);

    }

    //3.implement View.OnClickListener
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView t1;
        TextView t2;
        ImageView imageView;
        CheckBox checkBox;

        MyViewHolder(View view){
            super(view);
            t1 = (TextView) view.findViewById(R.id.tv_title);
            t2 = (TextView) view.findViewById(R.id.tv_detail);
            imageView = (ImageView) view.findViewById(R.id.movie_img);
            checkBox = view.findViewById(R.id.chekcbox);
            //3-1. setClickListener
            view.setOnClickListener((View.OnClickListener) this);
            viewHolderCount++;
        }
        //4.Create onClick method
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //interface , and method
            onClickListener.onListItemClick(position);
        }
    }
    //5.add a parameter of ListItemClickListener listener
    public MovieAdapter(List<Movie> movie_list, ListItemClickListener listener){
        this.movie_list = movie_list;
        onClickListener = listener;
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
        int bg = ColorUtility.getViewHolderBackgroundColorFromInstance(context,viewHolderCount);
        view.setBackgroundColor(bg);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Movie movie = movie_list.get(position);
        holder.t1.setText(movie.getTitle());
        holder.t2.setText(movie.getDetail());

        holder.checkBox.setChecked(movie.getSelected());

        //getting image
        Context context = MainActivity.getInstance().getApplicationContext();
        int id = context.getResources().getIdentifier(movie.getImg(), "drawable", context.getPackageName());
        holder.imageView.setImageResource(id);


        Log.d("------",movie.getTitle());
        Log.d("------", String.valueOf(movie.getSelected()));

        setAnimation(holder.itemView, position);
        setFadeAnimation(holder.itemView);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    movie_list.get(position).setIsSelected(true);
                }else{
                    movie_list.get(position).setIsSelected(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }


    //A1.Animation
    private void setAnimation(View view, int position){
        Context c = view.getContext();
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setFadeAnimation(View view){
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }
}
