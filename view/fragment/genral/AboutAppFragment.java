package com.example.bloodbank.view.fragment.genral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.homeCycle.home.MoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutAppFragment extends Fragment {

    @BindView(R.id.fragment_about_app_toolbar)
    Toolbar fragmentAboutAppToolbar;
    @BindView(R.id.fragment_about_app_arrow_back_iv)
    ImageView fragmentAboutAppArrowBackIv;
    private Unbinder unbinder;

    public AboutAppFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_about_app_arrow_back_iv)
    public void onViewClicked() {
        Fragment fragment = requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_home);
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_home, new MoreFragment())
                    .commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
