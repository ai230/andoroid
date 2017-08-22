package com.example.yamamotoai.addressbook;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactsFragment extends Fragment {


    public ContactsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ContactsAdapter adapter = new ContactsAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        FloatingActionButton add = (FloatingActionButton) view.findViewById(R.id.addButton);
        return view;
    }
}
