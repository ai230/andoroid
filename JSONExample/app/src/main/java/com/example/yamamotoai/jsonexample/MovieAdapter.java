package com.example.yamamotoai.jsonexample;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by yamamotoai on 2017-07-25.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
    private static int viewHolderCount;
    CardView cv;
    boolean[] checkBoxState;
    private List<Movie> movie_list;
    public static int[] checkedMovie = new int[200];
    public static int count=0;

    public MovieAdapter(List<Movie> movie_list) {
        this.movie_list = movie_list;
        checkBoxState = new boolean[movie_list.size()];
        viewHolderCount = 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemview);
        int bg = ColorUtils.getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(bg);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //    Movie movie = movie_list.get(position);
        holder.title.setText(movie_list.get(position).getTitle());
        holder.genre.setText(movie_list.get(position).getGenre());
        holder.year.setText(movie_list.get(position).getYear());
        holder.cast.setText(movie_list.get(position).getCast());
        holder.movieCheck.setChecked(movie_list.get(position).isSelected());
        //setOnClickListener for checkbox
        holder.movieCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_list.get(position).setSelected(true);
                checkedMovie[count] = position;
                count++;
                // Toast.makeText(v.getContext(),"item checked " + position ,Toast.LENGTH_SHORT).show();
            }
        });
        // Here you apply the animation when the view is bound
        // setAnimation(holder.itemView, position);
        // Set the view to fade in
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }

    //innerclass creates a view for a single row of data items.

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre, cast;
        public CheckBox movieCheck;

        public MyViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.cv);
            title = (TextView) view.findViewById(R.id.tv_title);
            year = (TextView) view.findViewById(R.id.tv_year);
            genre = (TextView) view.findViewById(R.id.tv_genre);
            cast = (TextView) view.findViewById(R.id.tv_cast);
            movieCheck = (CheckBox) view.findViewById(R.id.cb_movie);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    Toast.makeText(v.getContext(), "you have clicked an item: "+clickedPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
