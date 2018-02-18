package com.yamamotoai.fragmentanimation;


import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.yamamotoai.fragmentanimation.databinding.FragmentForthBinding;

/**
 * Created by yamamotoai on 2018-02-18.
 */

public class ForthFragment extends Fragment {

    FragmentForthBinding mBinding;

    private final String urlAtmVideo = "https://s3.ca-central-1.amazonaws.com/dojotech/uploads/wizard_vids/Using+an+ATM.mp4";

    public static ForthFragment newInstance() {
        ForthFragment fragment = new ForthFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forth, container, false);

        initVideoView(urlAtmVideo, mBinding.video);

        return mBinding.getRoot();
    }

    private void initVideoView(String url, final VideoView videoView) {
        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoURI(Uri.parse(url));
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setVideoViewPlay(videoView);
                return false;
            }
        });

    }

    private void setVideoViewPlay(VideoView videoView) {
        if (videoView.isPlaying()) {
            videoView.pause();
        } else {
            videoView.start();
        }
    }

}
