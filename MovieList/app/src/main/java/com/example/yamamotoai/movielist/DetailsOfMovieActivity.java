package com.example.yamamotoai.movielist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by yamamotoai on 2017-07-19.
 */

public class DetailsOfMovieActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvYear;
    TextView tvDetail;
    ImageView titleImg;
    private static DetailsOfMovieActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_movie);
        instance = this;

        titleImg = (ImageView) findViewById(R.id.title_img);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvDetail = (TextView) findViewById(R.id.tv_detail);

        String mTitle = getIntent().getStringExtra("mTitle");
        String mDetail = getIntent().getStringExtra("mDetail");
        String mYear = getIntent().getStringExtra("mYear");
        String mImg = getIntent().getStringExtra("mImg");

        tvTitle.setText(mTitle);
        tvDetail.setText(mDetail);
        tvYear.setText("(" + mYear + ")");
        int id = getResources().getIdentifier(mImg, "drawable", getPackageName());
        titleImg.setImageResource(id);
    }

}
