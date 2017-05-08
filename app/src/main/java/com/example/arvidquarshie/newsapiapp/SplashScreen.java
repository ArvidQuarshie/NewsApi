package com.example.arvidquarshie.newsapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

 View easySplashScreenView = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(LauncherActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.holo_red_light)
                .withHeaderText("News Api Integration")
                .withFooterText("Copyright 2017")
                .withBeforeLogoText("Arvid Quarshie")
                .withAfterLogoText("Welome")
                .create();

        setContentView(easySplashScreenView);

    }
}

