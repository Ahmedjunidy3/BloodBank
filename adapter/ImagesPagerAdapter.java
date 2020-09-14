package com.example.bloodbank.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bloodbank.view.fragment.splashCycle.FirstImageFragment;
import com.example.bloodbank.view.fragment.splashCycle.SecondImageFragment;
import com.example.bloodbank.view.fragment.splashCycle.ThirdImageFragment;


public class ImagesPagerAdapter extends FragmentPagerAdapter {

    public ImagesPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new FirstImageFragment();
            case 1: return new SecondImageFragment();
            default: return new ThirdImageFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
