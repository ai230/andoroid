package com.example.yamamotoai.midproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yamamotoai on 2017-08-03.
 */

public class PageFragment extends Fragment implements TODOAdapter.ListItemClickListener {

    private static final String ARG_PARAM = "page";
//    private int mParam;
    private OnFragmentInteractionListener mListener;


    private List<TODO> todoListTemp;

    private RecyclerView recyclerView;
    private TODOAdapter todoAdapter;
//    public static int todoListSize;

    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, page);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        todoListTemp = new ArrayList<>();
        organizedGroup();

        // Inflate the layout for this fragment
//        int page = getArguments().getInt(ARG_PARAM, 0);
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        todoAdapter = new TODOAdapter(todoListTemp, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoAdapter);

//        todoListSize = MainActivity.todoList.size();
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(int position) {
        Log.d("---","click");
        Intent intent = new Intent(getActivity().getApplicationContext(), AdditionActivity.class);
        TODO todo = todoListTemp.get(position);
        String date = todo.getDate();
        String title = todo.getTitle();
        Toast.makeText(getContext(), "TODO: #" + position + " " + date + " " + title, Toast.LENGTH_SHORT).show();
        intent.putExtra("TODOObjEdit", todo);
        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    public void organizedGroup(){

        Log.d("---", "organized");
        for(TODO todo: MainActivity.todoList){

//            Log.d("---","todoTemp1 " + todo.getGroup());
//            Log.d("---","selectedTab " + MainActivity.selectedTabName);

            if(todo.getGroup().matches(MainActivity.selectedTabName)){
                Log.d("---","ok");
                Log.d("---","todoTemp1 " + todo.getGroup());
                Log.d("---","selectedTab " + MainActivity.selectedTabName);
                todoListTemp.add(todo);
            }
        }
    }

}