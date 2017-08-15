package com.example.yamamotoai.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-14.
 */

public class DatabaseBandler extends SQLiteOpenHelper{

    //TODO)1.create a database name
    public static String DATABASE_NAME = "Bookdb";
    //TODO)2.current version ob database
    public static int DATABASE_VERSION = 1;

    //TODO)3.create a constractor
    public DatabaseBandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //TODO)4.create a table name
    private static String TABLE_NAME = "book";
    //TODO)5.All keys used in table with their data type & constrains
    private static final String KEY_ID = "bookid";
    private static final String KEY_NAME = "title";
    private static final String KEY_AUTHOR = "author";

    //TODO)6.create a query statment for creating a table in database
    private static final String CREATE_TABLE_BOOKS =
            "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER " + "PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NAME + " TEXT,"
                    + KEY_AUTHOR + " TEXT" + ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO)7.create a tablebook
            db.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //if you have changed or added or removed colmns in table
        //delete a table and will create new table then this method will be called
        db.execSQL("DROP TABLE IF EXIST" + CREATE_TABLE_BOOKS);
        this.onCreate(db);
    }

    public void addBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //get the reference to writeable DB
        ContentValues value = new ContentValues();
        //Create  Key & value pair
        value.put(KEY_NAME,book.getTitle());
        value.put(KEY_AUTHOR,book.getAuthor());

        //insert the data into table
        sqLiteDatabase.insert(TABLE_NAME,null, value);
        //close the database connection
        sqLiteDatabase.close();

    }
}
