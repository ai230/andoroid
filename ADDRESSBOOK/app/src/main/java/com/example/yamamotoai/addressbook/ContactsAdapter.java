package com.example.yamamotoai.addressbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yamamotoai.addressbook.data.AddressBookDatabaseHelper;
import com.example.yamamotoai.addressbook.data.DatabaseDescription;

import java.util.ArrayList;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>{

    private Cursor cursor = null;

    public void notifiChange(Cursor cursor){
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        //display only name
        //need id for database operation
        TextView textContact;
        long rowID;

        public ContactViewHolder(View itemView) {
            super(itemView);
            //dynamically create textview
            textContact = (TextView) itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setRowID(long rowID){
            this.rowID = rowID;
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        return new ContactViewHolder(view);
    }


    //sets the text of the list item to display the search
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.setRowID(cursor.getLong(cursor.getColumnIndex(DatabaseDescription.Contact._ID)));
        holder.textContact.setText(cursor.getString(cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_NAME)));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(cursor != null){
            return cursor.getCount();
        }
        return count;
    }
}
