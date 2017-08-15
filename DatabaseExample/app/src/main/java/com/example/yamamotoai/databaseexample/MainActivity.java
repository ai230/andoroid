package com.example.yamamotoai.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;

    private EditText editTextTitle;
    private EditText editTextAuthor;

    DatabaseBandler db = new DatabaseBandler(this);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        editTextTitle = (EditText) findViewById(R.id.editText_title);
        editTextAuthor = (EditText) findViewById(R.id.editText_author);

//        db.addBook(new Book("WINGS OF FIRE","APJ KALAM"));

    }

    public void addBookBtnClicked(View v){
        db.addBook(new Book(editTextTitle.getText().toString(),editTextAuthor.getText().toString()));
        Toast.makeText(this, "DATABASE CREATED SUCCESFULLY",
                Toast.LENGTH_LONG).show();
    }

}
