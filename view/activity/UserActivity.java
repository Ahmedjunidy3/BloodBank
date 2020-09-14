package com.example.bloodbank.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.userCycle.LoginFragment;
import com.example.bloodbank.view.fragment.userCycle.RegisterFragment;

import static com.example.bloodbank.utility.HelperMethod.replaceFragment;

public class UserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_login);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_activity_login, new LoginFragment())
                    .commit();
        }
    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_activity_login);
        if (count == 0) {
            if (fragment instanceof LoginFragment) {
                Intent intent = new Intent(this, SplashCyclesActivity.class);
                startActivity(intent);
            }
            else if (fragment instanceof RegisterFragment) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_activity_login, new LoginFragment())
                        .commit();
            }
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    }

