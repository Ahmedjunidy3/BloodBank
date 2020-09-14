package com.example.bloodbank.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.view.activity.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;
    public void initFragment(){
        baseActivity= (BaseActivity) getActivity();
        baseActivity.baseFragment = this;

    }
    public void onBack(){

    }

}
