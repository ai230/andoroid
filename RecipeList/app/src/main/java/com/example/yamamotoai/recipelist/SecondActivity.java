package com.example.yamamotoai.recipelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-22.
 */

public class SecondActivity extends AppCompatActivity{

    TextView recipeNameTextView;
    TextView recipeFullDescriptionTextView;
    ImageView recipeImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recipeImageView = (ImageView)findViewById(R.id.recipeImageView);
        recipeNameTextView = (TextView)findViewById(R.id.recipeNameTV);
        recipeFullDescriptionTextView = (TextView)findViewById(R.id.recipeFullDiscriptionTV);

        String recipeNameStr = getIntent().getStringExtra("recipeNameIntent");
        String recipeFullDescriptionStr = getIntent().getStringExtra("recipeFullDescriptionIntent");
        String recipeImageStr = getIntent().getStringExtra("recipeImageIntent");

        int id = getResources().getIdentifier(recipeImageStr, "drawable", getPackageName());
        recipeImageView.setImageResource(id);
        recipeNameTextView.setText(recipeNameStr);
        recipeFullDescriptionTextView.setText(recipeFullDescriptionStr);
    }
}
