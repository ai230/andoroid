package com.example.yamamotoai.recipelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by yamamotoai on 2017-07-24.
 */

public class WebviewActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String recipeUrlIntent = getIntent().getStringExtra("recipeUrlIntent");
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(recipeUrlIntent);
    }
}
