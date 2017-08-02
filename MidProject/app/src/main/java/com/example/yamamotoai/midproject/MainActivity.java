package com.example.yamamotoai.midproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TODOAdapter.ListItemClickListener{

    private List<TODO> todoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TODOAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(todoList == null){
//            Log.d("---","todoList = new ArrayList<>();");
//             todoList = new ArrayList<>();
//        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        todoAdapter = new TODOAdapter(todoList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoAdapter);

        Log.d("---before", String.valueOf(todoList.size()));
        TODO todo1 = (TODO)getIntent().getSerializableExtra("TODOObj");
        if(todo1 != null){
            Log.d("---", todo1.getTitle());
            todoList.add(todo1);
        }
        Log.d("---after", String.valueOf(todoList.size()));
//        prepareTODO();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent1 = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_settings:
                Intent intent2 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void prepareTODO(){

        TODO todo = new TODO("2017/06/10","Lorem ipsum","group","Lorem ipsum dolor sit amet, consectetur.");
        todoList.add(todo);
        todo = new TODO("2017/06/12","Lorem ipsum","group","Lorem ipsum dolor sit amet, consectetur.");
        todoList.add(todo);
        todo = new TODO("2017/06/13","Lorem ipsum","group","Lorem ipsum dolor sit amet, consectetur.");
        todoList.add(todo);

        Log.d("---", String.valueOf(todoList.size()));
    }

    @Override
    public void onListItemClick(int position) {

        Log.d("---","click");
        Intent intent = new Intent(MainActivity.this, AdditionActivity.class);
        TODO todo = todoList.get(position);
        String date = todo.getDate();
        String title = todo.getTitle();
        Toast.makeText(this, "TODO: #" + position + " " + date + " " + title, Toast.LENGTH_SHORT).show();
//        String recipeName = r.getRecipeName();
//        String recipeFullDescription = r.getRecipeDescription();
//        String recipeImage = r.getRecipeImage();
//        intent.putExtra("recipeNameIntent",recipeName);
//        intent.putExtra("recipeFullDescriptionIntent",recipeFullDescription);
//        intent.putExtra("recipeImageIntent",recipeImage);
//        String recipeURL = r.getUrl();
//        intent.putExtra("recipeUrlIntent",recipeURL);
        intent.putExtra("TODOObjEdit", todo);
        startActivity(intent);
    }


}
