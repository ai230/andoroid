package com.example.yamamotoai.addressbook;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yamamotoai.addressbook.data.DatabaseDescription;


/**
 * Created by yamamotoai on 2017-08-16.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>{

    private Cursor cursor = null;

    //create interface to handle

    ContactsAdapterInterface contactsAdapterInterface;
    public interface ContactsAdapterInterface
    {
        public void onItemClick(Uri uri);
    }

    //constracter
    public ContactsAdapter (ContactsAdapterInterface clicklistener){
        this.contactsAdapterInterface = clicklistener;
    }

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
                    //Adapter is attached to Fragment so interface should be implemented in the fragment
                    contactsAdapterInterface.onItemClick(DatabaseDescription.Contact.buildContactUri(rowID));
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
