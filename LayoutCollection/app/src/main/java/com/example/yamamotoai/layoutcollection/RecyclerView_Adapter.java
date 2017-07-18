package com.example.yamamotoai.layoutcollection;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-18.
 */

public class RecyclerView_Adapter extends  RecyclerView.Adapter<RecyclerView_Adapter.NumberOfViewHolder>{
    private int numberOfListItems;

    class NumberOfViewHolder extends RecyclerView.ViewHolder {
        TextView ListItemNumberView;
        ImageView ListItemImageView;
        public NumberOfViewHolder(View itemView) {
            super(itemView);
//            ListItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
        }

        public void bind(int ListIndex){
            ListItemNumberView.setText(String.valueOf(ListIndex));
        }
    }

    @Override
    public NumberOfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NumberOfViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return numberOfListItems;
    }
}
