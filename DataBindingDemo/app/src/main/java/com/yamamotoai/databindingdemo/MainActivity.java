package com.yamamotoai.databindingdemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setcontentView
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //create user object
        User user = new User("Android","Developer","Google");

        //set the obj
        binding.setUser(user);
    }
}
