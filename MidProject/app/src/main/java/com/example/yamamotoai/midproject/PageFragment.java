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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-03.
 */

public class PageFragment extends Fragment implements TODOAdapter.ListItemClickListener {

    private static final String ARG_PARAM = "page";
    private int mParam;
    private OnFragmentInteractionListener mListener;

    private List<TODO> todoList1 = new ArrayList<>();
    private List<TODO> todoList2 = new ArrayList<>();
    private List<TODO> todoList3 = new ArrayList<>();

    private RecyclerView recyclerView;
    private TODOAdapter todoAdapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_PARAM);
        }


//        Log.d("---before", String.valueOf(todoList.size()));
        TODO todo1 = (TODO)getActivity().getIntent().getSerializableExtra("TODOObj");
        if(todo1 != null){
            Log.d("---after", todo1.getTitle());
//            todoList.add(todo1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        int page = getArguments().getInt(ARG_PARAM, 0);
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        todoAdapter = new TODOAdapter(todoList1, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(todoAdapter);
        Log.d("---prepare","todo");
        prepareTODO();
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
//        Intent intent = new Intent(getActivity().getApplicationContext(), AdditionActivity.class);
//        TODO todo = todoList.get(position);
//        String date = todo.getDate();
//        String title = todo.getTitle();
//        Toast.makeText(getContext(), "TODO: #" + position + " " + date + " " + title, Toast.LENGTH_SHORT).show();
//        intent.putExtra("TODOObjEdit", todo);
//        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void prepareTODO(){

        TODO todo = new TODO("2017-06-10","Lorem ipsum","G1","Lorem ipsum dolor sit amet, consectetur.");
        todoList1.add(todo);
//        todo = new TODO("2017-06-12","Lorem ipsum","G1","Lorem ipsum dolor sit amet, consectetur.");
//        todoList1.add(todo);
        todo = new TODO("2017-06-13","Lorem ipsum","G2","Lorem ipsum dolor sit amet, consectetur.");
        todoList2.add(todo);
        todo = new TODO("2017-08-13","Lorem ipsum","G3","Lorem ipsum dolor sit amet, consectetur.");
        todoList3.add(todo);

        Log.d("---", "prepared");
    }

}
