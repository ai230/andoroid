package com.example.yamamotoai.addressbook.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.yamamotoai.addressbook.R;

/**
 * Created by yamamotoai on 2017-08-21.
 */

public class AddressBookContetProvider extends ContentProvider {
    //1. Create instance of your database
    private AddressBookDatabaseHelper dbHelper;

    //2. URI Matcher that helps Contentprovider to determine the operation to perform
    //We have two URIs
        //1) that gives you all rows of contact
        //2) that gives you only one row of contact based on ID

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Constant values that are used with URIMATCHER to determine the operations to performed
    private static final int CONTACTS = 2;
    private static final int ONE_CONTACT = 1;

    //static block to configure this URIMATCHER
    static {
        //URI for contact table that will return all rows
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.Contact.TABLE_NAME,CONTACTS);

        //URI for contact table with specific #id value
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.Contact.TABLE_NAME + " /#", ONE_CONTACT);
    }

    @Override
    public boolean onCreate() {
        //create the database
        dbHelper = new AddressBookDatabaseHelper(getContext());
        return true;
    }

    //SELECT * FROM CONTACT or SELECT * FROM CONTACT WHERE CONTACT_ID = #VALUE
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DatabaseDescription.Contact.TABLE_NAME);

        switch (uriMatcher.match(uri))
        {
            case ONE_CONTACT:
                //contact with specific ID will be selected
                //add the ID value from URI to the QueryBuilder
                queryBuilder.appendWhere(DatabaseDescription.Contact._ID + " = "
                        + uri.getLastPathSegment());
                break;
            case CONTACTS:
                //all contacts will be selected
                break;
            default:
                throw new UnsupportedOperationException(
                        getContext().getString(R.string.invalid_delete_uri) + uri
                );
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    public void throwSQLException(){
        throw new SQLException(
                getContext().getString(R.string.invalid_insert_uri)
        );
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //uri it represents table in which data will be added
        //
        Uri newContact = null;

        switch (uriMatcher.match(uri))
        {
            case CONTACTS:
            //add the new data to the end of table
            //CONTACTS give you full table
            long rowID = dbHelper.getWritableDatabase().insert(
                    DatabaseDescription.Contact.TABLE_NAME,
                    null,//*nullColumnsHack
                    values
            );
                //*nullColumnsHack: SQLite doesn't support inserting empty row in table,
                //but it accept null value
                if(rowID > 0){
                    newContact = DatabaseDescription.Contact.buildContactUri(rowID);

                    //notify the cursor for newly added data
                    getContext().getContentResolver().notifyChange(uri,null);
                }else {
                    //database operation gets fail throw insert faild exception
                    throw new SQLException(
                            getContext().getString(R.string.invalid_updated_uri)
                    );
                }
                break;
            default:
                throw new SQLException(
                        getContext().getString(R.string.invalid_updated_uri)
                );
        }

        return newContact;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int noOfRowsDeleted;
        //check the uri for one contact
        switch(uriMatcher.match(uri))
        {
            case ONE_CONTACT:
                //get from the uri the id of contact for delete
                String id = uri.getLastPathSegment();
                noOfRowsDeleted = dbHelper.getWritableDatabase().delete(
                        DatabaseDescription.Contact.TABLE_NAME,
                        DatabaseDescription.Contact._ID + " = " + id,
                        selectionArgs
                );
                break;
            default:
                throw new SQLException(
                        getContext().getString(R.string.invalid_updated_uri)
                );
        }
        //if changes are successfully notify the changes in cursor
        if(noOfRowsDeleted > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return noOfRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int noOfRowsUpdated;
        switch (uriMatcher.match(uri))
        {
            case ONE_CONTACT:
                String id = uri.getLastPathSegment();
                noOfRowsUpdated = dbHelper.getWritableDatabase().update(
                        DatabaseDescription.Contact.TABLE_NAME,
                        values,
                        DatabaseDescription.Contact._ID + " = " + id,  // e.g. Where id = 5
                        selectionArgs
                );
                break;
            default:
                throw new SQLException(
                        getContext().getString(R.string.invalid_updated_uri)
                );
        }

        //if changes are successfully notify the changes in cursor
        if(noOfRowsUpdated > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return noOfRowsUpdated;
    }
}
