package com.yamamotoai.fragmentanimation;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.yamamotoai.fragmentanimation.databinding.FragmentFirstBinding;

/**
 * Created by yamamotoai on 2018-02-17.
 */

public class FirstFragment extends Fragment {

    FragmentFirstBinding mBinding;

    private final String urlAtmVideo = "https://s3.ca-central-1.amazonaws.com/dojotech/uploads/wizard_vids/Using+an+ATM.mp4";


    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_first, container, false);

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
