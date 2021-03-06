package com.example.yamamotoai.recipelist;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-07-22.
 */

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    int lastPosition = -1;
    Context context;
    String msg = "---";
    private List<Recipe> recipeList;
    final private ListItemClickListener onClickListner;
    public  interface ListItemClickListener {
        void onListItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView recipeImage;
        TextView recipeNameTextview;
        TextView recipeShortDescriptionTextview;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipeImage);
            recipeNameTextview = (TextView) itemView.findViewById(R.id.recipeName);
            recipeShortDescriptionTextview = (TextView) itemView.findViewById(R.id.recipeShortDiscription);
            checkBox = itemView.findViewById(R.id.checkbox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickListner.onListItemClick(position);
            Log.d(msg,"onClick: " + position);
        }
    }

    public RecipeAdapter(List<Recipe> recipeList, ListItemClickListener listener){
        this.recipeList = recipeList;
        this.onClickListner = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_list_row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecipeAdapter.MyViewHolder holder, final int position) {

        Recipe r = recipeList.get(position);
        holder.recipeNameTextview.setText(r.getRecipeName());
        holder.recipeShortDescriptionTextview.setText(r.getRecipeDescription());
        holder.checkBox.setChecked(r.getSelected());

        int id = context.getResources().getIdentifier(r.getRecipeImage(), "drawable", context.getPackageName());
        holder.recipeImage.setImageResource(id);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    recipeList.get(position).setSelected(true);
                }else{
                    recipeList.get(position).setSelected(false);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Recipe r = recipeList.get(position);
                Recipe duplicatedRecipe = new Recipe(r.getRecipeImage(), r.getRecipeName(), r.getRecipeDescription(), r.getUrl());
                recipeList.add(position+1,duplicatedRecipe);
                notifyDataSetChanged();
                Toast.makeText(context, "Recipe: #" + position + " is duplicated.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        setAnimation(holder.itemView, position);
        Log.d(msg,r.getRecipeName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    private void setAnimation(View view, int position)
    {
        Log.d(msg, "position: "+String.valueOf(position) + " lastposition: " + String.valueOf(lastPosition));
        if(position > lastPosition)
        {
            Log.d(msg,"anim1");
            Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }
}
