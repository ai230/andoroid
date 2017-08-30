package com.example.yamamotoai.todolist;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-26.
 */

public class SearchResultsActivity extends Fragment {


    ListView listView;
    ListInGroupAdapter adapter;
    private List<TODO> todo_List_temp = new ArrayList<TODO>();
    private List<TODO> todo_List = new ArrayList<TODO>();

    DatabaseHandler db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

//        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);
        //read new data from database
        db = new DatabaseHandler(getActivity());
        todo_List = db.readDatabase(todo_List);
        return view;

    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
////        searchView.setIconifiedByDefault(false);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d("","");
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d("",newText);
//
//                if(!newText.matches(""))
//                    createList(newText);
//                return false;
//            }
//        });
//        return true;
//    }

    public void createList(View view, String newText){

        todo_List_temp = new ArrayList<>();
        for(TODO item: todo_List){
            if(item.getTitle().contains(newText)){
                todo_List_temp.add(item);
            }
        }
        //initialize listview
        listView = (ListView)view.findViewById(R.id.listview_second);
        //create adapter
        adapter = new ListInGroupAdapter(getActivity(), todo_List_temp, false);
        //set adapter to the listview
        listView.setAdapter(adapter);
        //set item listener to the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                listGroupInFragmentInterface.onDisplayAddingPageForEditing(String.valueOf(todoListInGroup.get(position).getId()), selectedGroupName);
            }
        });
    }
    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.searchable);
////        handleIntent(getIntent());
//    }
//
//    public void handleIntent(Intent intent){
//
//        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            //use the query to search the data
//        }
//
//    }

}
