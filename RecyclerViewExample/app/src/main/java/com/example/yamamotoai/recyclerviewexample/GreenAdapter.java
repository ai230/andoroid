package com.example.yamamotoai.recyclerviewexample;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-17.
 */

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder>{

    private int mNumberListItems;
    private static int viewHolderCount;//total number of viewholder
    //a.Create val of object
    final private ListItemClickListener listItemClickListener;

    //b.implement interface
    public interface ListItemClickListener {
        void onListItemClick(int index);
    }

    public GreenAdapter(int numberListOfItems, ListItemClickListener listener){
        this.mNumberListItems = numberListOfItems;
        this.listItemClickListener = listener;
        viewHolderCount = 0;

    }

    //3.create an inner class//c.implenents View.OnClickListener
    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView ListItemNumberView;
        TextView viewholderInstance;

        NumberViewHolder(View itemView){
            super(itemView);
            ListItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewholderInstance = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            //setClickListener
            itemView.setOnClickListener((View.OnClickListener) this);

        }


        public void bind(int ListIndex) {
            ListItemNumberView.setText(String.valueOf(ListIndex));
            viewholderInstance.setText("----"+ viewHolderCount);
            viewHolderCount++;
        }

        //d.Create onClick method
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //interface , and method
            onClickListener.onListItemClick(position);
        }
    }

    //4.this create object of numberviewholder class
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        //create a object of LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(context);
        //create layout for textview
        View view = inflater.inflate(R.layout.number_list_item,parent,false);
        //need to pass this view to NumberViewHolder
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        int bg = ColorUtility.getViewHolderBackgroundColorFromInstance(context,viewHolderCount);
        Log.d("---", String.valueOf(viewHolderCount));
        view.setBackgroundColor(bg);
        return viewHolder;
    }

    //5.
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    //2.total number of items
    @Override
    public int getItemCount() {
        return mNumberListItems;
    }
}
