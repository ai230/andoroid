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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;

    private EditText editTextTitle;
    private EditText editTextAuthor;

    DatabaseHandler db = new DatabaseHandler(this);
    ListView lv_books;
    BookAdapter bookAdapter;
    List<Book> listbooks;

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
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        lv_books = (ListView) findViewById(R.id.list_book);
        editTextTitle = (EditText) findViewById(R.id.editText_title);
        editTextAuthor = (EditText) findViewById(R.id.editText_author);

        //TODO)8.read all books from and add it into list
        listbooks = db.getAllBooks();
        Toast.makeText(this, "GET ALL BOOKS", Toast.LENGTH_SHORT).show();
        List<Integer> listID = new ArrayList<Integer>();
        List<String> listTitle = new ArrayList<String>();
        List<String> listAuthor = new ArrayList<String>();

        for(int i = 0; i < listbooks.size(); i++){
            listID.add(i,listbooks.get(i).getId()); //why do you need i?
            listTitle.add(i,listbooks.get(i).getTitle()); //why do you need i?
            listAuthor.add(i,listbooks.get(i).getAuthor()); //why do you need i?
        }
        bookAdapter = new BookAdapter(this, listID, listTitle, listAuthor);
        lv_books.setAdapter(bookAdapter);
        lv_books.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "You click on position:"+position, Toast.LENGTH_SHORT).show();

                Book book = db.getSelectedBook(position);

                editTextTitle.setText(book.getTitle());
                editTextAuthor.setText(book.getAuthor());
            }
        });
    }

    public void addBookBtnClicked(View v){
        db.addBook(new Book(editTextTitle.getText().toString(),editTextAuthor.getText().toString()));
        Toast.makeText(this, "DATABASE CREATED SUCCESFULLY",
                Toast.LENGTH_LONG).show();
    }


}
