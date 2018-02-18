package com.yamamotoai.fragmentanimation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yamamotoai on 2018-02-17.
 */

public class AppUtils {

    public static void navigateToFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.setCustomAnimations(
                R.anim.right_in,
                R.anim.left_out,
                R.anim.left_in,
                R.anim.right_out);

        ft.replace(R.id.fragment_container, fragment)
                .commit();
    }

    public static void navigateBackToFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.setCustomAnimations(
                R.anim.left_in,
                R.anim.right_out,
                R.anim.right_in,
                R.anim.left_out);

        ft.replace(R.id.fragment_container, fragment)
                .commit();
    }
}
