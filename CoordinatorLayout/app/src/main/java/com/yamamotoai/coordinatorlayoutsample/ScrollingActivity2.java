package com.yamamotoai.coordinatorlayoutsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollingActivity2 extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private boolean mIsTheTitleVisible = false;
    private boolean mIsThePersonDetailsContainerVisible = true;

    private static final int ALPHA_ANIMATIONS_DURATION = 300;
    private static final float PERCENTAGE_TO_HIDE_PERSON_DETAILS = 0.3f;
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;

    private Toolbar toolbar;
    private TextView tvTitleToolBar;
    private AppBarLayout appBarLayout;
    private LinearLayout linearLayoutPersonDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);

        findViewById();

        appBarLayout.addOnOffsetChangedListener(this);

        startAlphaAnimation(linearLayoutPersonDetails, 0, View.VISIBLE);
        startAlphaAnimation(toolbar, 0, View.INVISIBLE);

    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitleToolBar = (TextView) findViewById(R.id.tvTitleToolBar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        linearLayoutPersonDetails = (LinearLayout) findViewById(R.id.linearLayout);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnPersonDetails(percentage);
        handleToolBarTitleVisibility(percentage);
    }

    private static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void handleAlphaOnPersonDetails(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_PERSON_DETAILS) {
            if (mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(linearLayoutPersonDetails, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsThePersonDetailsContainerVisible = false;
            }
        } else {
            if (!mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(linearLayoutPersonDetails, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsThePersonDetailsContainerVisible = true;
            }
        }
    }

    private void handleToolBarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

}
