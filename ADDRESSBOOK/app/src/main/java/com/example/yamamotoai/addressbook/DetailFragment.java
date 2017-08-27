package com.example.yamamotoai.addressbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yamamotoai.addressbook.data.DatabaseDescription;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CONTACT_LOADER= 0;
    private Uri contactUri;
    private static Uri deleteUri;
    TextView nameTextView;
    TextView phoneTextView;
    TextView emailTextView;
    TextView streetTextView;
    TextView cityTextView;
    TextView stateTextView;
    TextView zipTextView;

    //object for interface
    DetailFragmentInterface detailFragmentInterface;
    //2methods for edit and delete
    public interface DetailFragmentInterface{
        void onEditContact(Uri uri);
        void onContactDeleted(Uri uri);
    }

    public static class DialogDeleteFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.confirm_message)
                    .setPositiveButton(R.string.confirm_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getActivity().getContentResolver().delete(
                                    deleteUri,
                                    null,
                                    null
                            );
                        }
                    })
                    .setNegativeButton(R.string.confirm_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing
                        }
                    })
                    .create();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_datails, container, false);

        //set the references of Textview
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        emailTextView = (TextView) view.findViewById(R.id.emailTextView);
        streetTextView = (TextView) view.findViewById(R.id.streetTextView);
        cityTextView = (TextView) view.findViewById(R.id.cityTextView);
        stateTextView = (TextView) view.findViewById(R.id.stateTextView);
        zipTextView = (TextView) view.findViewById(R.id.zipTextView);
        //get the bundle value for selected contact URI
        Bundle arg = getArguments();
        contactUri = arg.getParcelable(MainActivity.CONTACT_URI);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //initializing
        detailFragmentInterface = (DetailFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detailFragmentInterface = null;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(
                getActivity(),
                contactUri,
                null,
                null,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //pass the data to UI if your contact datail exist
        if(data != null && data.moveToFirst()){
            int nameIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_NAME);
            int phoneIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_PHONE);
            int emailIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_EMAIL);
            int streetIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_STREET);
            int cityIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_CITY);
            int stateIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_STATE);
            int zipIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_ZIP);

            nameTextView.setText(data.getString(nameIndex));
            phoneTextView.setText(data.getString(phoneIndex));
            emailTextView.setText(data.getString(emailIndex));
            streetTextView.setText(data.getString(streetIndex));
            cityTextView.setText(data.getString(cityIndex));
            stateTextView.setText(data.getString(stateIndex));
            zipTextView.setText(data.getString(zipIndex));
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(CONTACT_LOADER,null,this);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.fragment_details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_edit:
                detailFragmentInterface.onEditContact(contactUri);
                break;
            case R.id.action_delete:
                deleteUri = contactUri;
                DialogDeleteFragment dialogFragment = new DialogDeleteFragment();
                dialogFragment.show(getActivity().getFragmentManager(),"dialog");
                detailFragmentInterface.onContactDeleted(DatabaseDescription.Contact.CONTENT_URI);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
