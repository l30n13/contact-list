package com.inonity.buddybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashScreenActivity_0 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_activity_0);
        //Splash screen

        int SPLASH_DISPLAY_LENGTH = 2000;
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
