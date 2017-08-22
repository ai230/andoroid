package com.example.yamamotoai.addressbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yamamotoai.addressbook.data.AddressBookDatabaseHelper;
import com.example.yamamotoai.addressbook.data.DatabaseDescription;

import java.util.ArrayList;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>{

    Context context;
    Cursor cursor;

    public ContactsAdapter(Context context){
        this.context = context;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView tvContact;
        long rowID;

        public ContactViewHolder(View itemView) {
            super(itemView);
        }

        public void setRowID(int rowID){
            this.rowID = rowID;
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.fragment_contacts,parent,false);
        View view = inflater.inflate(android.R.layout.simple_list_item_1,parent);
        return new ContactViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        cursor.moveToPosition(position);

        holder.tvContact.setText(cursor.getString(cursor.getColumnIndex(DatabaseDescription.Contact.COLUMN_NAME)));
        holder.setRowID(cursor.getLong(cursor.getColumnIndex(DatabaseDescription.Contact._ID)));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
