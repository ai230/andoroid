package com.example.yamamotoai.midproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yamamotoai on 2017-08-03.
 */

public class PageFragment extends Fragment implements TODOAdapter.ListItemClickListener {

    private static final String ARG_PARAM = "page";
    private int mParam;
    private OnFragmentInteractionListener mListener;


    private List<TODO> todoListTemp;

    private RecyclerView recyclerView;
    private TODOAdapter todoAdapter;
    public static int todoListSize;

    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, page);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam = getArguments().getInt(ARG_PARAM);
//        }
//
//
////        Log.d("---before", String.valueOf(todoList.size()));
//        TODO todo1 = (TODO)getActivity().getIntent().getSerializableExtra("TODOObj");
//        if(todo1 != null){
//            Log.d("---after", todo1.getTitle());
////            todoList.add(todo1);
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d("---PageFragment","input data");
//        try {
//            FileInput(MainActivity.file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        TODO newTodo = (TODO)getActivity().getIntent().getSerializableExtra("TODOObj");
//        if(newTodo != null){
//            Log.d("---after", newTodo.getTitle());
//            MainActivity.todoList.add(newTodo);
//        }
        Log.d("---onCreateView1", String.valueOf(MainActivity.todoList.size()));
        todoListTemp = new ArrayList<>();
        organizedGroup();
        Log.d("---onCreateView2", String.valueOf(todoListTemp.size()));

        // Inflate the layout for this fragment
//        int page = getArguments().getInt(ARG_PARAM, 0);
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        Log.d("---todoAdapter", String.valueOf(todoListTemp.size()));
        todoAdapter = new TODOAdapter(todoListTemp, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(todoAdapter);


        Log.d("---onCreateView--","");

        todoListSize = MainActivity.todoList.size();
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
            Log.d("---","todoTemp1 " + todo.getGroup());
            Log.d("---","selectedTab " + MainActivity.selectedTabName);
            if(todo.getGroup().matches(MainActivity.selectedTabName)){
                Log.d("---","ok");
                todoListTemp.add(todo);
            }
        }
    }


//    public void FileInput(File file) throws IOException {
////        List<String> data = new ArrayList<>();
//        BufferedReader bufferedReader = null;
//        FileInputStream fileInputStream;
//        InputStreamReader inputStreamReader;
//        try {
//            fileInputStream = new FileInputStream(file);
//            inputStreamReader = new InputStreamReader(fileInputStream);
//            bufferedReader = new BufferedReader(inputStreamReader);
//
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] tokens = line.split(",");
//                int id = Integer.parseInt(tokens[0]);
//                String date = tokens[1];
//                String title = tokens[2];
//                String group = tokens[3];
//                String content = tokens[4];
//                TODO todo = new TODO(id, date, title, group, content);
//                todoList.add(todo);
//                Log.d("---line",line);
//            }
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//
//            } catch (IOException ioe) {
//                System.out.println("Error in closing the BufferedReader");
//            }
//        }
//    }
}