package com.example.bloodbank.view.fragment.splashCycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.bloodbank.R;

import java.util.Objects;

public class SplashFragment extends Fragment {

    public SplashFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_splash_container, new ImagesContainerFragment())
                    .commitAllowingStateLoss();
        }, 5000);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

}
