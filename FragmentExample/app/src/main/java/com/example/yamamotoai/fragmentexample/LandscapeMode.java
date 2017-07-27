package com.example.yamamotoai.fragmentexample;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yamamotoai on 2017-07-26.
 */

public class LandscapeMode extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("---","landscape");
        return inflater.inflate(R.layout.landscape_mode,container,false);
    }
}
