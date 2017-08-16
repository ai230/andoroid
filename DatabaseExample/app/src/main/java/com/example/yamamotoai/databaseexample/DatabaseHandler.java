package com.example.yamamotoai.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-15.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //TODO)1.create a database name
    public static String DATABASE_NAME = "Bookdb";
    //TODO)2.current version ob database
    public static int DATABASE_VERSION = 1;

    //TODO)3.create a constractor
    public DatabaseHandler(Context context){
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

    //TODO)9.creat a method that is getting all the data from database
    public List<Book> getAllBooks(){
        List<Book> books = new LinkedList<>();//in order
        //TODO)10.create a select query
        String query = "Select * From " + TABLE_NAME;
        //TODO)11.get instance of database in READABLE data
        SQLiteDatabase db = this.getReadableDatabase();
        //TODO)12.get curser object for result of query
        Cursor cursor = db.rawQuery(query,null);
        //TODO)13.go over result and build_book object and add it to the list
        Book book = null;
        if(cursor.moveToFirst()){
            do{
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));//firest colnm is ID
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                books.add(book);
            }while(cursor.moveToNext());
        }
        return books;
    }

    public Book getSelectedBook(int bookId){
        Book book = new Book();
        String query = "Select * From " + TABLE_NAME + " WHERE " + KEY_ID + " = " + bookId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            book.setId(Integer.parseInt(cursor.getString(0)));//firest colnm is ID
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
        }
        return book;
    }

    public int updateBook(Book book){

        //TODO)1.Open database with writeble mode
        SQLiteDatabase db = this.getWritableDatabase();
        //TODO)2.Create Contentvalues (key & value pairs)
        ContentValues value = new ContentValues();
        value.put("title",book.getTitle());
        value.put("author",book.getAuthor());

        //TODO)3.updating a row using update() //last value is simgle value but we need to pass String[]
        int result = db.update(TABLE_NAME, value, KEY_ID + " = ? ", new String[] {String.valueOf(book.getId())});
        //TODO)4.Close db
        db.close();
        return result;
    }

    public int deleteBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, KEY_ID + " = ? ", new String[] {String.valueOf(book.getId())});
        db.close();
        return result;
    }
}
