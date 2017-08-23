package com.example.yamamotoai.addressbook;

import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yamamotoai.addressbook.data.DatabaseDescription;

public class MainActivity extends AppCompatActivity implements ContactsFragment.ContactFragmentInterface, AddEditFragment.AddEditFragmentInterface{

    public static  final String CONTACT_URI = "contacturi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        displayContactsFragment(R.id.fragmentContainer, DatabaseDescription.Contact.CONTENT_URI);

//        displayAddEditFragment(R.id.fragmentContainer, DatabaseDescription.Contact.CONTENT_URI);

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
        // Create the fragment using FragmentTransaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(viewId, addEditFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void onAddContact() {
        displayAddEditFragment(R.id.fragmentContainer, DatabaseDescription.Contact.CONTENT_URI);
    }

    //interface ContactsFragment
    @Override
    public void onContactSelected(Uri uri) {
        DetailFragment detailFragment = new DetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
    }
}
