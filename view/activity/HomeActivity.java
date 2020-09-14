package com.example.bloodbank.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.homeCycle.home.HomeFragment;
import com.example.bloodbank.view.fragment.homeCycle.home.MoreFragment;
import com.example.bloodbank.view.fragment.genral.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;


public class HomeActivity  extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.activity_home_nav_bottom)
    BottomNavigationView activityHomeNavBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_home);
        if (fragment == null) {
            fragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_activity_home, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        activityHomeNavBottom.setOnNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_bottom_profile_btn: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new PersonalFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
            case R.id.nav_bottom_home_btn: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new HomeFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
            case R.id.nav_bottom_more_btn: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new MoreFragment())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
            case R.id.nav_bottom_notificat_btn: {
         //       Intent intent = new Intent(this, NotificationsActivity.class);
          //      startActivity(intent);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_activity_home);
        if (count == 0) {
            if (fragment instanceof HomeFragment) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}
