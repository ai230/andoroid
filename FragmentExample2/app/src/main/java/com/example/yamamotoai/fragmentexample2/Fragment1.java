package com.example.yamamotoai.fragmentexample2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yamamotoai on 2017-07-26.
 */

public class Fragment1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("------","Fragment1");
//        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_01, container, false);
    }

}
