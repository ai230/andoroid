package com.project.ai.todolist.Fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.ai.todolist.R;
import com.project.ai.todolist.TODO;
import com.project.ai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-30.
 */

public class SearchResultFragment extends Fragment {

    ListView listView;
    AllTodolistAdapter adapter;
    private List<TODO> todo_List_temp = new ArrayList<TODO>();
    private List<TODO> todo_List_temp_done = new ArrayList<TODO>();
    private List<TODO> todo_List = new ArrayList<TODO>();

    FloatingActionButton fab_add;

    DatabaseHandler db;

    private String newText;
    private SearchResultInterface searchResultInterface;
    public interface SearchResultInterface{
        void onDisplayAddingPageInSearch();
        void onDisplayAddingPageForEditingInSearch(String SelectedTodoId, String selectedGroupName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        searchResultInterface = (SearchResultInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        searchResultInterface = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_all_todolist, container, false);
        //read new data from database
        db = new DatabaseHandler(getActivity());
        todo_List = db.readDatabase(todo_List);
        Bundle bundle = getArguments();
        if(bundle != null)
            newText = bundle.getString("newText");

        //If strings in searchview is "" all todolist show
        todo_List_temp = new ArrayList<>();
        todo_List_temp_done = new ArrayList<>();
//        if(newText.equals(""))
//            todo_List_temp = todo_List;
//        if(!newText.equals(""))
        createList(view, newText);


        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_todolist);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResultInterface.onDisplayAddingPageInSearch();
            }
        });

        //initialize listview
        listView = (ListView)view.findViewById(R.id.listview_second);
        //create adapter
        adapter = new AllTodolistAdapter(getActivity(), todo_List_temp);
        //set adapter to the listview
        listView.setAdapter(adapter);
        //set item listener to the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TODO todo_temp = todo_List_temp.get(position);
                searchResultInterface.onDisplayAddingPageForEditingInSearch(String.valueOf(todo_temp.getId()), todo_temp.getGroup());
            }
        });

        return view;
    }


    public void createList(View view, String newText){

        if(newText.matches("")){
            for (TODO item: todo_List){
                if(item.isDone()){
                    todo_List_temp_done.add(item);
                } else{
                    todo_List_temp.add(item);
                }
            }
        }else{
            for(TODO item: todo_List){
                if(item.getTitle().toLowerCase().contains(newText.toLowerCase())
                        || item.getContent().toLowerCase().contains(newText.toLowerCase())){
                    if(item.isDone()){
                        todo_List_temp_done.add(item);
                    } else{
                        todo_List_temp.add(item);
                    }

                }
            }
        }
        todo_List_temp.addAll(todo_List_temp_done);

    }

}
