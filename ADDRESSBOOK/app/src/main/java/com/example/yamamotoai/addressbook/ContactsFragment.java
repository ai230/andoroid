package com.example.yamamotoai.addressbook;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yamamotoai.addressbook.data.DatabaseDescription;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, ContactsAdapter.ContactsAdapterInterface{


    ContactsAdapter adapter;
    private int contact_loader =0;

    //declaration
    ContactFragmentInterface contactFragmentInterface;

    //ContactsAdapterInterface
    @Override
    public void onItemClick(Uri uri) {
        contactFragmentInterface.onContactSelected(uri);
    }

    public interface ContactFragmentInterface
    {
        void onAddContact();
        void onContactSelected(Uri uri);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        adapter = new ContactsAdapter(new ContactsAdapter.ContactsAdapterInterface() {
            @Override
            public void onItemClick(Uri uri) {
                contactFragmentInterface.onContactSelected(uri);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getBaseContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
        //improveing the performance
        recyclerView.setHasFixedSize(true);

        FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactFragmentInterface.onAddContact();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //initialization
        contactFragmentInterface = (ContactFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        contactFragmentInterface = null;
    }

    //create a loader object and start loading the data into the cursor
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(
                getActivity(),
                DatabaseDescription.Contact.CONTENT_URI,
                null,
                null,
                null,
                null);

        return cursorLoader;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initialize Loader
        getLoaderManager().initLoader(contact_loader,null,this);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.moveToFirst()){
            do {
                int name = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_NAME);
                String namedata = data.getString(name);
                Log.d("data name ", namedata);
            }while (data.moveToNext());
        }
        adapter.notifiChange(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.notifiChange(null);
    }
}
