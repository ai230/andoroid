package com.example.yamamotoai.todolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yamamotoai on 2017-07-28.
 */

public class TabPagerAdapter extends FragmentPagerAdapter{

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new FirstTab();
            case 1:
                return new SecondTab();
            case 2:
                return new ThirdTab();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
