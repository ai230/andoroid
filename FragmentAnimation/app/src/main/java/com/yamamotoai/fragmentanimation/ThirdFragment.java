package com.yamamotoai.fragmentanimation;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.yamamotoai.fragmentanimation.databinding.FragmentThirdBinding;

/**
 * Created by yamamotoai on 2018-02-17.
 */

public class ThirdFragment extends Fragment {

    FragmentThirdBinding mBinding;

    private final String urlCardVideo = "https://s3.ca-central-1.amazonaws.com/dojotech/uploads/wizard_vids/Paying+yourself+first.mp4";

    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false);

        initVideoView(urlCardVideo, mBinding.video);

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

    public void callFromOut() {
        Log.d("ThirdFragment", "Call from out third");
    }
}
