package com.example.yamamotoai.addressbook;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yamamotoai.addressbook.data.DatabaseDescription;

public class MainActivity extends AppCompatActivity
        implements ContactsFragment.ContactFragmentInterface,
        AddEditFragment.AddEditFragmentInterface,
        DetailFragment.DetailFragmentInterface {

    public static final String CONTACT_URI = "contacturi";
    private int viewId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //If screensize is phone
        viewId = R.id.fragmentContainer;
        int screensize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screensize == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            //If screen is tablet
            viewId = R.id.rightPaneContainer;
        }else{
            displayContactsFragment(viewId, DatabaseDescription.Contact.CONTENT_URI);
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


    //methods for ContactsFragmentInterface when add button is clicked
    @Override
    public void onAddContact() {
        displayAddEditFragment(viewId, null);
    }

    //methods for ContactsFragmentInterface
    @Override
    public void onContactSelected(Uri uri) {

        DetailFragment detailFragment = new DetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewId, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        //create a bundle obj that will pass selected row uri to detailFragment
        Bundle bundle = new Bundle();
        bundle.putParcelable(CONTACT_URI, uri);
        detailFragment.setArguments(bundle);
    }

    @Override
    public void onAddEditCompleted(Uri uri, int message) {
        //remove the fragment(AddEditFragment)
        getSupportFragmentManager().popBackStack();
        Snackbar snackbar = Snackbar.make(findViewById(viewId), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.confirm_ok, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do nothing so far
            }
        });
        snackbar.show();
    }

    //mothods for DetailFragmentInterface
    @Override
    public void onEditContact(Uri uri) {
        displayAddEditFragment(viewId, uri);
    }

    @Override
    public void onContactDeleted(final Uri uri) {
        onAddEditCompleted(uri, R.string.confirm_snackbar_deleted);
    }

}
