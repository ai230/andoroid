package com.project.ai.todolist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.project.ai.todolist.TODO;

import java.util.List;

/**
 * Created by yamamotoai on 2017-08-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "TODOLIST1db";
    public static int DATABASE_VERSION = 1;

    private static String TABLE_NAME = "TODOLIST";
    private static String KEY_ID = "todoId";
    private static String KEY_DATE = "todoDate";
    private static String KEY_TITLE = "todoTitle";
    private static String KEY_GROUP = "todoGroup";
    private static String KEY_CONTENT = "todoContent";
    private static String KEY_ISDONE = "todoIsDone";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_DATE + " TEXT," +
                    KEY_TITLE + " TEXT," +
                    KEY_GROUP + " TEXT," +
                    KEY_CONTENT + " TEXT," +
                    KEY_ISDONE + " BIT);";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXSISTS " + TABLE_NAME;

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<TODO> readDatabase(List<TODO> list){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * From " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        TODO todo = null;
        if(cursor.moveToFirst()){
            do{
                todo = new TODO();
                todo.setId(Integer.parseInt(cursor.getString(0)));
                todo.setDate(cursor.getString(1));
                todo.setTitle(cursor.getString(2));
                todo.setGroup(cursor.getString(3));
                todo.setContent(cursor.getString(4));
                todo.setDone(cursor.getInt(5) > 0);
                list.add(todo);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public long writeDatabase(TODO todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE,todo.getDate());
        values.put(KEY_TITLE,todo.getTitle());
        values.put(KEY_GROUP,todo.getGroup());
        values.put(KEY_CONTENT,todo.getContent());
        values.put(KEY_ISDONE,todo.isDone());
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public void updateDatabase(TODO todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE,todo.getDate());
        values.put(KEY_TITLE,todo.getTitle());
        values.put(KEY_GROUP,todo.getGroup());
        values.put(KEY_CONTENT,todo.getContent());
        values.put(KEY_ISDONE,todo.isDone());
        long updateRowId = db.update(TABLE_NAME, values, KEY_ID + " = ? ", new String[]{String.valueOf(todo.getId())});
        db.close();
    }

    public void updateDatabaseDoneToTrue(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ISDONE,1);
        long updateRowId = db.update(TABLE_NAME, values, KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteFromDatabase(String[] ids){
        SQLiteDatabase db = this. getWritableDatabase();
        String args = TextUtils.join(", ", ids);
//        db.execSQL(String.format("DELETE FROM " + TABLE_NAME + " WHERE ids IN (%s);", args));
        db.delete(TABLE_NAME, KEY_ID + " = ? ", ids);
        db.close();
    }


//    public int getId(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "Select * From " + TABLE_NAME + " where " + KEY_TITLE + " = ";
//        Cursor cursor = db.rawQuery(query, null);
//        TODO todo = null;
//        if(cursor.moveToFirst()){
//            do{
//                todo = new TODO();
//                todo.setId(Integer.parseInt(cursor.getString(0)));
//                todo.setDate(cursor.getString(1));
//                todo.setTitle(cursor.getString(2));
//                todo.setGroup(cursor.getString(3));
//                todo.setContent(cursor.getString(4));
//                list.add(todo);
//            }while (cursor.moveToNext());
//        }
//        return list;
//    }
}
