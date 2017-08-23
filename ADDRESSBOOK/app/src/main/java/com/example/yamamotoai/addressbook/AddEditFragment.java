package com.example.yamamotoai.addressbook;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yamamotoai.addressbook.data.DatabaseDescription;

import static android.R.attr.colorButtonNormal;
import static android.R.attr.data;

/**
 * Created by yamamotoai on 2017-08-16.
 */
//This is Fragment that is attached an activity...
//Context --> getContext(), getBaseCopntext(), getActivity(), this, classname.this

//use LoaderManager.LoaderCallback<> interface
//for loadering
public class AddEditFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    //Every Loader needs a Unique ID integer constant for Loader
    private static final int CONTACT_LOADER = 0;
    //create a contact uri // this comes from MainActivity
    private Uri contactUri;

    private TextInputLayout nameTextInput;
    private TextInputLayout phoneTextInput;
    private TextInputLayout emailTextInput;
    private TextInputLayout streetTextInput;
    private TextInputLayout cityTextInput;
    private TextInputLayout stateTextInput;
    private TextInputLayout zipTextInput;
    private FloatingActionButton saveContactFab;
    //Create a view for fragment

    AddEditFragmentInterface addEditFragmentInterface;
    public interface AddEditFragmentInterface
    {
        public void onAddEditCompleted(Uri uri);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Inflate the GUI and get the reference for EditText
        View view = inflater.inflate(R.layout.fragment_addedit, container, false);

        nameTextInput = (TextInputLayout) view.findViewById(R.id.nameTextInputLayout);
        phoneTextInput = (TextInputLayout) view.findViewById(R.id.phoneTextInputLayout);
        emailTextInput = (TextInputLayout) view.findViewById(R.id.emailTextInputLayout);
        streetTextInput = (TextInputLayout) view.findViewById(R.id.streetTextInputLayout);
        cityTextInput = (TextInputLayout) view.findViewById(R.id.cityTextInputLayout);
        stateTextInput = (TextInputLayout) view.findViewById(R.id.stateTextInputLayout);
        zipTextInput = (TextInputLayout) view.findViewById(R.id.zipTextInputLayout);

        saveContactFab = (FloatingActionButton) view.findViewById(R.id.saveFloatingActionButton);
        saveContactFab.setOnClickListener(saveDataListener);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addEditFragmentInterface = (AddEditFragmentInterface)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addEditFragmentInterface = null;
    }

    private View.OnClickListener saveDataListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            saveContact();//save info into database
            addEditFragmentInterface.onAddEditCompleted(DatabaseDescription.Contact.CONTENT_URI);
        }
    };

    private void saveContact(){
        ContentValues values = new ContentValues();
        //key:column name value:from textInput
        values.put(DatabaseDescription.Contact.COLUMN_NAME, nameTextInput.getEditText().getText().toString());
        values.put(DatabaseDescription.Contact.COLUMN_PHONE, phoneTextInput.getEditText().getText().toString());
        values.put(DatabaseDescription.Contact.COLUMN_EMAIL, emailTextInput.getEditText().getText().toString());
        values.put(DatabaseDescription.Contact.COLUMN_STREET, streetTextInput.getEditText().getText().toString());
        values.put(DatabaseDescription.Contact.COLUMN_CITY, cityTextInput.getEditText().getText().toString());
        values.put(DatabaseDescription.Contact.COLUMN_STATE, stateTextInput.getEditText().getText().toString());
        values.put(DatabaseDescription.Contact.COLUMN_ZIP, zipTextInput.getEditText().getText().toString());

        //this Uri is used to call contentResolver insert the data into address content
        Uri newContactUri = getActivity()
                .getContentResolver()
                .insert(DatabaseDescription.Contact.CONTENT_URI,values);

        Toast.makeText(getActivity(), "DATA INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        //change the toast to snackbar
        //snackbar is ntification feedback to the user
        //and you add actions to snackbar like undo, cancel, ok
    }

    //1.this one will create a Loader start Loader the data from Content provider
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        //Create a CursorLoader based on ID
        //use getActivity() because this is fragment
        CursorLoader cursorLoader = new CursorLoader(
                getActivity(),//context
                contactUri,//uri of the contact to display
                null,//all columns//projection
                null,//all rows//selection
                null,//no wherecause//selectionArgs
                null);//order
        return cursorLoader;
    }

    //2.Once the loading of data is finished then this method is called
    // from here will give the data back to UI
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Called when loading is completed
        if(data != null && data.moveToFirst()){
            int nameIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_NAME);
            int phoneIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_PHONE);
            int emailIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_EMAIL);
            int streetIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_STREET);
            int cityIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_CITY);
            int stateIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_STATE);
            int zipIndex = data.getColumnIndex(DatabaseDescription.Contact.COLUMN_ZIP);

            nameTextInput.getEditText().setText(data.getString(nameIndex));
            phoneTextInput.getEditText().setText(data.getString(phoneIndex));
            emailTextInput.getEditText().setText(data.getString(emailIndex));
            streetTextInput.getEditText().setText(data.getString(streetIndex));
            cityTextInput.getEditText().setText(data.getString(cityIndex));
            stateTextInput.getEditText().setText(data.getString(stateIndex));
            zipTextInput.getEditText().setText(data.getString(zipIndex));

        }
    }

    //3.It will reset Loader
    @Override
    public void onLoaderReset(Loader loader) {

    }

}
