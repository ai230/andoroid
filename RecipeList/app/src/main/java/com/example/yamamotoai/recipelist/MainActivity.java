package com.example.yamamotoai.recipelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener{

    String msg = "---";
    private List<Recipe> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;

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

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recipeAdapter = new RecipeAdapter(recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);
        prepareRecipeData();
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

    public void prepareRecipeData(){
        Recipe recipe = new Recipe("recipe_1", "Seven Layer Taco Dip", "Seven layer taco dip made with refried beans, sour cream, and salsa is the perfect platter for parties and family get-togethers.", "http://allrecipes.com/recipe/19673/seven-layer-taco-dip/?internalSource=rotd&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%201");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_2", "Baked Buffalo Chicken Dip", "Chef John puts a new spin on an iconic party favourite with this cheesy, spicy, and tangy dip. Serve with celery and crackers.", "http://allrecipes.com/recipe/221131/baked-buffalo-chicken-dip/?internalSource=staff%20pick&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%204");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_3", "Perfect Crab-Stuffed Mushrooms", "Button mushrooms stuffed with crab and Monterey Jack cheese make a delicious bite-size appetizer. Your guests are sure to be dazzled!", "http://allrecipes.com/recipe/229826/perfect-crab-stuffed-mushrooms/?internalSource=staff%20pick&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%203");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_4", "Strawberry Bruschetta", "This is a delicious variation of the popular tomato based appetizer. The strawberries are warm and sweet and the sugar is caramelized and crunchy! Your guests will love it!", "http://allrecipes.com/recipe/69225/strawberry-bruschetta/?internalSource=staff%20pick&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%205");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_5", "Coconut Shrimp", "These crispy shrimp are rolled in a coconut beer batter before frying. For dipping sauce, I use orange marmalade, mustard and horseradish mixed to taste.", "http://allrecipes.com/recipe/17753/coconut-shrimp-i/?internalSource=hub%20recipe&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%2031");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_1", "Seven Layer Taco Dip", "Seven layer taco dip made with refried beans, sour cream, and salsa is the perfect platter for parties and family get-togethers.", "http://allrecipes.com/recipe/19673/seven-layer-taco-dip/?internalSource=rotd&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%201");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_2", "Baked Buffalo Chicken Dip", "Chef John puts a new spin on an iconic party favourite with this cheesy, spicy, and tangy dip. Serve with celery and crackers.", "http://allrecipes.com/recipe/221131/baked-buffalo-chicken-dip/?internalSource=staff%20pick&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%204");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_3", "Perfect Crab-Stuffed Mushrooms", "Button mushrooms stuffed with crab and Monterey Jack cheese make a delicious bite-size appetizer. Your guests are sure to be dazzled!", "http://allrecipes.com/recipe/229826/perfect-crab-stuffed-mushrooms/?internalSource=staff%20pick&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%203");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_4", "Strawberry Bruschetta", "This is a delicious variation of the popular tomato based appetizer. The strawberries are warm and sweet and the sugar is caramelized and crunchy! Your guests will love it!", "http://allrecipes.com/recipe/69225/strawberry-bruschetta/?internalSource=staff%20pick&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%205");
        recipeList.add(recipe);
        recipe = new Recipe("recipe_5", "Coconut Shrimp", "These crispy shrimp are rolled in a coconut beer batter before frying. For dipping sauce, I use orange marmalade, mustard and horseradish mixed to taste.", "http://allrecipes.com/recipe/17753/coconut-shrimp-i/?internalSource=hub%20recipe&referringId=76&referringContentType=recipe%20hub&clickId=cardslot%2031");
        recipeList.add(recipe);
    }

    @Override
    public void onListItemClick(int position) {

        Toast.makeText(this, "Recipe: #" + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
        Recipe r = recipeList.get(position);
//        String recipeName = r.getRecipeName();
//        String recipeFullDescription = r.getRecipeDescription();
//        String recipeImage = r.getRecipeImage();
//        intent.putExtra("recipeNameIntent",recipeName);
//        intent.putExtra("recipeFullDescriptionIntent",recipeFullDescription);
//        intent.putExtra("recipeImageIntent",recipeImage);
        String recipeURL = r.getUrl();
        intent.putExtra("recipeUrlIntent",recipeURL);
        startActivity(intent);
    }

    public void onClickSelectAllBtn(View view) {
        for(Recipe r: recipeList){
            r.setSelected(true);
            recipeAdapter.notifyDataSetChanged();
        }
    }

    public void onClickClearAllBtn(View view) {
        for(Recipe r: recipeList){
            r.setSelected(false);
            recipeAdapter.notifyDataSetChanged();
        }
    }

    public void onClickDeleteBtn(View view) {
        int arraySize = recipeList.size();
        for(int i=0; i<arraySize; i++){
            if(recipeList.get(i).getSelected() == true) {
                recipeList.remove(i);
                Log.d(msg,"deleted: " + i);
                i -= 1;
                arraySize -= 1;
                recipeAdapter.notifyDataSetChanged();
            }
        }
    }
}
