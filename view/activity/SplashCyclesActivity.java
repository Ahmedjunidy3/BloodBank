package com.example.bloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.splashCycle.SplashFragment;

import android.content.SharedPreferences;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class SplashCyclesActivity extends AppCompatActivity {


    //    private static int SPLASH_TIME_OUT = 3000;
//    //Hooks
//    View splash_upper_design,splash_bottom_design,splash_logo_design;
//    //Animations
//    Animation splash_upper_Animation,splash_bottom_Animation,splash_logo_Animation;
//
//    SharedPreferences  slidingscreen;
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.activity_splash_container);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_splash_container, new SplashFragment())
                    .commit();
        }
//
//        splash_bottom_design = findViewById(R.id.splash_bottom_design);
//        splash_upper_design = findViewById(R.id.splash_upper_desgin);
//        splash_logo_design = findViewById(R.id.splash_logo);
//        //Animation Calls
//        splash_upper_Animation = AnimationUtils.loadAnimation(this, R.anim.splash_upper_animation);
//        splash_bottom_Animation = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);
//        splash_logo_Animation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_animation);
//
//
//        splash_upper_design.setAnimation(splash_upper_Animation);
//        splash_logo_design.setAnimation(splash_logo_Animation);
//        splash_bottom_design.setAnimation(splash_bottom_Animation);
//        //Splash Screen Code to call new Activity after some time
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                slidingscreen = getSharedPreferences("SlidingScreen",MODE_PRIVATE);
//                boolean isFriestTime = slidingscreen.getBoolean("firstTime",true);
//
//                if(isFriestTime) {
//
//                    SharedPreferences.Editor editor = slidingscreen.edit();
//                    editor.putBoolean("firstTime",false);
//                    editor.commit();
//                    Intent intent = new Intent(getApplicationContext(),SliderActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else {
//                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        }, SPLASH_TIME_OUT);
//    }
    }
}
