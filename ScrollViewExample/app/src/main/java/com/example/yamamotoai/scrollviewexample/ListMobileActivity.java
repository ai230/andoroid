//package com.example.yamamotoai.scrollviewexample;
//
//import android.app.ListActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ListView;
//import android.widget.Toast;
//
///**
// * Created by yamamotoai on 2017-07-11.
// */
//
//public class ListMobileActivity extends ListActivity{
//
//    static final String[] MOBILE_OS = new String[] { "Android", "iOS",
//            "WindowsMobile", "Blackberry"};
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //setListAdapter(new ArrayAdapter<String>(this, R.layout.list_mobile,
//        //		R.id.label, MOBILE_OS));
//        setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
//
//
//    }
//
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//
//        //get selected items
//        String selectedValue = (String) getListAdapter().getItem(position) + " Position: " + position;
//        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
//
//    }
//
//}
