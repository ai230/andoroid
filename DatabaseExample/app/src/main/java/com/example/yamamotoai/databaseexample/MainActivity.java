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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;

    private EditText editTextTitle;
    private EditText editTextAuthor;

    private DatabaseHandler db = new DatabaseHandler(this);
    private ListView lv_books;
    private BookAdapter bookAdapter;
    private List<Book> listbooks = new ArrayList<Book>();

    private Integer selectedID;
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

        lv_books = (ListView) findViewById(R.id.list_book);
        editTextTitle = (EditText) findViewById(R.id.editText_title);
        editTextAuthor = (EditText) findViewById(R.id.editText_author);

        readDatabase();
        createListView();
    }

    public void addBookBtnClicked(View v){
        db.addBook(new Book(editTextTitle.getText().toString(),editTextAuthor.getText().toString()));
        readDatabase();
        createListView();
        Toast.makeText(this, "DATABASE CREATED SUCCESFULLY",
                Toast.LENGTH_SHORT).show();

    }

    public void updateListBtnClicked(View view){
        //create empty book object
        Book book = new Book();
        book.setId(selectedID);
        book.setTitle(editTextTitle.getText().toString());
        book.setAuthor(editTextAuthor.getText().toString());
        int rowAffected = db.updateBook(book);
        readDatabase();
        createListView();
        Toast.makeText(this, rowAffected + " DATABASE UPDATED SUCCESFULLY", Toast.LENGTH_SHORT).show();
    }

    public void deleteBtnClicked(View view){
        //create empty book object
        Book book = new Book();
        book.setId(selectedID);
        book.setTitle(editTextTitle.getText().toString());
        book.setAuthor(editTextAuthor.getText().toString());
        int rowAffected = db.deleteBook(book);
        readDatabase();
        createListView();
        Toast.makeText(this, rowAffected + " DATABASE DELETED SUCCESFULLY", Toast.LENGTH_SHORT).show();

    }

    public void sortByTitleBtnClicked(View view){
        Collections.sort(listbooks, CustmComparator.sortTitle);
        createListView();
        Toast.makeText(this, "SORT BY TITLE", Toast.LENGTH_SHORT).show();
    }

    public void sortByAuthorBtnClicked(View view){
        Collections.sort(listbooks, CustmComparator.sortAuthor);
        createListView();
        Toast.makeText(this, "SORT BT AUTHOR", Toast.LENGTH_SHORT).show();
    }

    public void readDatabase(){
        //TODO)8.read all books from and add it into list
        listbooks = db.getAllBooks();
        Toast.makeText(this, "GET ALL BOOKS", Toast.LENGTH_SHORT).show();
    }


    public void createListView(){
        final List<Integer> listID = new ArrayList<Integer>();
        List<String> listTitle = new ArrayList<String>();
        List<String> listAuthor = new ArrayList<String>();

        for(int i = 0; i < listbooks.size(); i++){
            listID.add(i,listbooks.get(i).getId());
            listTitle.add(i,listbooks.get(i).getTitle());
            listAuthor.add(i,listbooks.get(i).getAuthor()); //why do you need i?
        }
        bookAdapter = new BookAdapter(this, listID, listTitle, listAuthor);
        lv_books.setAdapter(bookAdapter);
        lv_books.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedID = listbooks.get(position).getId();
                Book book = db.getSelectedBook(listbooks.get(position).getId());
                String t = book.getTitle();
                String a = book.getAuthor();
                editTextTitle.setText(book.getTitle());
                editTextAuthor.setText(book.getAuthor());
                Toast.makeText(getApplicationContext(), "You click on ID:"+listbooks.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
