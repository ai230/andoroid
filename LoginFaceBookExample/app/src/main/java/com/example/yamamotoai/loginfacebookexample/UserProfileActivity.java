package com.example.yamamotoai.loginfacebookexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yamamotoai on 2017-08-29.
 */

public class UserProfileActivity extends AppCompatActivity {

    private TextView txt_name;
    private Button btn_logout;
    private ImageView img_user;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txt_name = (TextView) findViewById(R.id.txt_name);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        shareDialog = new ShareDialog(this);

        Bundle args = getIntent().getExtras();
        String firstName = args.get("first_name").toString();
        String lastName = args.get("last_name").toString();
//        String imgUrl = args.get("imgUrl").toString();
        txt_name.setText(" " + firstName + " " + lastName);

//        new DownloadImage((ImageView)findViewById(R.id.img_profile)).execute(imgUrl);//background process

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(UserProfileActivity.this, MainActivity1.class);
                startActivity(login);
                finish();
            }
        });

    }

    //code for downloading image from facebook
    //create a inner class that uses bitmap
    //inner class using Asnyc for background proceccing

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {


        ImageView bmImage;

        public DownloadImage (ImageView imageView){
            this.bmImage = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //Retreiving the url for
            String urlDisplay = params[0];
            Bitmap myImage = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                myImage = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bmImage.setImageBitmap(bitmap);

        }
    }

}
