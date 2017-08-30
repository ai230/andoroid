package com.example.yamamotoai.loginfacebookexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by yamamotoai on 2017-08-29.
 */

public class MainActivity1 extends AppCompatActivity {

    CallbackManager callbackManager;
    ProfileTracker profileTracker;// if someshing is changed
    AccessTokenTracker tokenTracker;
    LoginButton loginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main1);
        Log.d("---","Main1");
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                //do something
                nextActivity(currentProfile);
            }
        };
        tokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        //Start tracking the data
        profileTracker.startTracking();
        tokenTracker.startTracking();
        loginButton = (LoginButton)findViewById(R.id.login_button1);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(MainActivity1.this, "AAA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        loginButton.setReadPermissions("public_profile");
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent intent = new Intent(MainActivity1.this, UserProfileActivity.class);
            intent.putExtra("first_name", profile.getFirstName());
            intent.putExtra("last_name", profile.getLastName());
            intent.putExtra("imageUri", profile.getProfilePictureUri(200,200));
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onStop() {
        super.onStop();
        profileTracker.stopTracking();
        tokenTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
