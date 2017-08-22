package com.example.yamamotoai.addressbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yamamotoai on 2017-08-21.
 */

public class AddressBookDatabaseHelper extends SQLiteOpenHelper {

    //create a database name and version
    public static final String DATABASE_NAME = "AddressBook";
    public static final int DATABASE_VERSION = 1;

    //Create a default constructor
    public AddressBookDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL query for creating the contact table
        //for primary key _id
        final String CREATE_CONTACTS_TABLE = "CREATE TABLE " +
                DatabaseDescription.Contact.TABLE_NAME + " ( " +
                DatabaseDescription.Contact._ID + " integer primary key, " +
                DatabaseDescription.Contact.COLUMN_NAME + " TEXT, " +
                DatabaseDescription.Contact.COLUMN_PHONE + " TEXT, " +
                DatabaseDescription.Contact.COLUMN_EMAIL + " TEXT, " +
                DatabaseDescription.Contact.COLUMN_STREET + " TEXT, " +
                DatabaseDescription.Contact.COLUMN_CITY + " TEXT, " +
                DatabaseDescription.Contact.COLUMN_STATE + " TEXT, " +
                DatabaseDescription.Contact.COLUMN_ZIP + " TEXT); ";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + DatabaseDescription.Contact.TABLE_NAME);
        onCreate(db);
    }
}
