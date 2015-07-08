package com.inonity.buddybook;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashScreenActivity_0 extends Activity {
    MediaPlayer mySplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_activity_0);
        //Splash screen
        mySplash=MediaPlayer.create(this,R.raw.splash_sound);
        mySplash.start();

        int SPLASH_DISPLAY_LENGTH = 1000;
        new android.os.Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashScreenActivity_0.this, HomeViewActivity_1.class);
                SplashScreenActivity_0.this.startActivity(splashIntent);
                SplashScreenActivity_0.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    }




