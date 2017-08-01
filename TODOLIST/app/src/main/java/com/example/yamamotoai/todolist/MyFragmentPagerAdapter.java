package com.example.yamamotoai.todolist;

        import android.content.Context;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

        import java.util.ArrayList;
        import java.util.List;


/**
 * Created by yamamotoai on 2017-07-29.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

//    MainActivity mainActivity;
    //    String[] tabs;
//    Context context;

    //    List<String> tabs = new ArrayList<>();
    String[] tabs = {"tabtabtab1","tab2","tab3"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
//        mainActivity = new MainActivity();
//        context = mainActivity.getContext();
//        tabs = context.getResources().getStringArray(R.array.tabs);
    }

    @Override
    public Fragment getItem(int position) {
        MainActivity.MyFragment myfragment = MainActivity.MyFragment.getInstance(position);
        return myfragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
