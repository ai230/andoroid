package com.example.yamamotoai.databaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-15.
 */

public class BookAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater=null;
    List<Integer> listId;
    List<String> listTitle;
    List<String> listAuthor;

    public BookAdapter(MainActivity mainActivity, List<Integer> listId, List<String> listTitle, List<String> listAuthor){
        this.listId = listId;
        this.listTitle = listTitle;
        this.listAuthor = listAuthor;
        context = mainActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class Holder{
        TextView bookId, bookTitle, bookAuthor;
    }
    @Override
    public int getCount() {
        return listId.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.list_row,null);
        holder.bookId = (TextView) rowView.findViewById(R.id.text_bookid);
        holder.bookTitle = (TextView) rowView.findViewById(R.id.text_booktitle);
        holder.bookAuthor = (TextView) rowView.findViewById(R.id.text_bookauthor);
        holder.bookId.setText(listId.get(position).toString());
        holder.bookTitle.setText(listTitle.get(position));
        holder.bookAuthor.setText(listAuthor.get(position));

        return rowView;
    }


}
