package com.example.yamamotoai.addressbook;

import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yamamotoai.addressbook.data.DatabaseDescription;

public class MainActivity extends AppCompatActivity
        implements ContactsFragment.ContactFragmentInterface,
        AddEditFragment.AddEditFragmentInterface,
        DetailFragment.DetailFragmentInterface {

    public boolean screensize_large = true;
    public static  final String CONTACT_URI = "contacturi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int screensize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screensize != Configuration.SCREENLAYOUT_SIZE_XLARGE)
            screensize_large = false;
        if(screensize_large == false) {
            displayContactsFragment(R.id.fragmentContainer, DatabaseDescription.Contact.CONTENT_URI);
        }
    }

    public void displayContactsFragment(int viewId, Uri contactUri){
        ContactsFragment contactsFragment = new ContactsFragment();
        // Create the fragment using FragmentTransaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(viewId, contactsFragment);
        fragmentTransaction.commit();

    }

    //create a object of fragment
    //display fragment for adding a new or editting an exsiting contacts
    //View ID:layoutid
    //contacturi:the path for
    public void displayAddEditFragment(int viewId, Uri contactUri){
        AddEditFragment addEditFragment = new AddEditFragment();

        //new cotact --> contacturi
        //editting contact --> contacturi + id
        if(contactUri != null){
            Bundle arg = new Bundle();
            arg.putParcelable(CONTACT_URI, contactUri);
            addEditFragment.setArguments(arg);
        }
        // Create the fragment using FragmentTransaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(viewId, addEditFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onAddContact() {
        displayAddEditFragment(R.id.fragmentContainer, null);
    }

    //methods for ContactsFragmentInterface
    @Override
    public void onContactSelected(Uri uri) {

            DetailFragment detailFragment = new DetailFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(screensize_large)
                transaction.replace(R.id.rightPaneContainer, detailFragment);
            else
                transaction.replace(R.id.fragmentContainer, detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            //create a bundle obj that will pass selected row uri to detailFragment
            Bundle bundle = new Bundle();
            bundle.putParcelable(CONTACT_URI, uri);
            detailFragment.setArguments(bundle);
    }

    @Override
    public void onAddEditCompleted(Uri uri) {
        //remove the fragment(AddEditFragment)
        getSupportFragmentManager().popBackStack();
        Toast.makeText(this,"Conpleted",Toast.LENGTH_SHORT).show();
    }

    //mothods for DetailFragmentInterface
    @Override
    public void onEditContact(Uri uri) {
        displayAddEditFragment(R.id.fragmentContainer, uri);
    }

    private Uri deleteUri;
    @Override
    public void onContactDeleted(Uri uri) {
        onAddEditCompleted(uri);
        //create a dialog fragment
        //if delete yes call delete() , if not do nothing
//        deleteUri = uri;
//        DialogFragment dialogFragment = DialogDeleteFragment.newInstance(R.string.confirm_title,uri);
//        dialogFragment.show(getFragmentManager(),"dialog");

    }

}
