package com.yamamotoai.fragmentanimation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yamamotoai.fragmentanimation.databinding.ContentMainBinding;

public class MainActivity extends AppCompatActivity {

    ContentMainBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.content_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBinding.nextButton.setOnClickListener(mClickListener);
        mBinding.backButtonContainer.setOnClickListener(mClickListenerBack);

        mBinding.fragmentContainer.addOnLayoutChangeListener(mLayoutChangeListener);
        YoutubeFragment fragment = new YoutubeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.youtube_view, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null && fragment instanceof FirstFragment) {
                AppUtils.navigateToFragment(getSupportFragmentManager(), SecondFragment.newInstance());
            } else if (fragment != null && fragment instanceof SecondFragment) {
                ((SecondFragment) fragment).callFromOut();
                AppUtils.navigateToFragment(getSupportFragmentManager(), ThirdFragment.newInstance());
            } else if (fragment != null && fragment instanceof ThirdFragment) {
                ((ThirdFragment) fragment).callFromOut();
                AppUtils.navigateToFragment(getSupportFragmentManager(), ForthFragment.newInstance());
            } else if (fragment != null && fragment instanceof ForthFragment) {
                AppUtils.navigateToFragment(getSupportFragmentManager(), FirstFragment.newInstance());
            } else {
                AppUtils.navigateToFragment(getSupportFragmentManager(), FirstFragment.newInstance());
            }
        }
    };

    View.OnClickListener mClickListenerBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null && fragment instanceof FirstFragment) {
                AppUtils.navigateBackToFragment(getSupportFragmentManager(), ForthFragment.newInstance());
            } else if (fragment != null && fragment instanceof SecondFragment) {
                ((SecondFragment) fragment).callFromOut();
                AppUtils.navigateBackToFragment(getSupportFragmentManager(), FirstFragment.newInstance());
            } else if (fragment != null && fragment instanceof ThirdFragment) {
                ((ThirdFragment) fragment).callFromOut();
                AppUtils.navigateBackToFragment(getSupportFragmentManager(), SecondFragment.newInstance());
            } else if (fragment != null && fragment instanceof ForthFragment) {
                AppUtils.navigateBackToFragment(getSupportFragmentManager(), ThirdFragment.newInstance());
            } else {
                AppUtils.navigateToFragment(getSupportFragmentManager(), ForthFragment.newInstance());
            }
        }
    };

    View.OnLayoutChangeListener mLayoutChangeListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null && fragment instanceof FirstFragment) {
                mBinding.nextButton.setText("first");
            } else if (fragment != null && fragment instanceof SecondFragment) {
                ((SecondFragment) fragment).callFromOut();
                mBinding.nextButton.setText("second");
            } else if (fragment != null && fragment instanceof ThirdFragment) {
                ((ThirdFragment) fragment).callFromOut();
                mBinding.nextButton.setText("third");
            } else if (fragment != null && fragment instanceof ForthFragment) {
                mBinding.nextButton.setText("Woowoo");
            }
        }
    };

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back pressed", Toast.LENGTH_SHORT).show();
    }
}
