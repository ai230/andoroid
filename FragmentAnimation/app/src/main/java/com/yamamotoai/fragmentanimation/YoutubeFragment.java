package com.yamamotoai.fragmentanimation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by yamamotoai on 2018-02-17.
 */

public class YoutubeFragment  extends Fragment {

    //memo : YoutubeFragmentは複雑なレイアウトでは使えない。他のレイアウトに重なると再生ストップする。

    // API key from google console
    private static final String API_KEY = "AIzaSyAGWgGOUoDzSz3Nvw9A1_8VfQPQrgmGPb4";

    // Video ID
    private static String VIDEO_ID = "EGy39OMyHzw";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.youtube_api, container, false);

        // create instance of YouTubePlayerSupportFragment
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        // add youtube layout
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        // initialize YouTube player fragment
        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {

            // success initializing YouTube player fragment
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.loadVideo(VIDEO_ID);
//                    player.play();
                }
            }

            // failed initializing YouTube player fragment
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });

        return rootView;
    }
}
