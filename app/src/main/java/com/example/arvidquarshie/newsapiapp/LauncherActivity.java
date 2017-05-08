package com.example.arvidquarshie.newsapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import gr.net.maroulis.library.EasySplashScreen;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class LauncherActivity extends AppCompatActivity {
    private TextView link1, link2, link3;
    private GifImageView mGifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        link1 = (TextView) findViewById(R.id.link1);
        link2 = (TextView) findViewById(R.id.link2);
        link3 = (TextView) findViewById(R.id.link3);
        mGifImageView = (GifImageView) findViewById(R.id.gifSplash);

        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, TechnologyRelatedActivity.class);
                startActivity(intent);
            }
        });

       try{
        GifDrawable gifFromResource = new GifDrawable(getResources(), R.drawable.globe_gif);
        mGifImageView.setImageDrawable(gifFromResource);

           gifFromResource.addAnimationListener((AnimationListener) LauncherActivity.this);}
    catch(Exception e){

    }
       }

}
