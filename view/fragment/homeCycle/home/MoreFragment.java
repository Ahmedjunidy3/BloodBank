package com.example.bloodbank.view.fragment.homeCycle.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.activity.UserActivity;
import com.example.bloodbank.view.fragment.genral.AboutAppFragment;
import com.example.bloodbank.view.fragment.genral.ContactUsFragment;
import com.example.bloodbank.view.fragment.genral.TheFavoritesFragment;
import com.example.bloodbank.view.fragment.homeCycle.notifications.NotificatSettingsFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MoreFragment extends Fragment {

    private Unbinder unbinder;

    public MoreFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.favs_tv_frag_more, R.id.contact_us_tv_frag_more, R.id.about_us_tv_frag_more, R.id.review_us_tv_frag_more, R.id.notificat_settings_tv_frag_more, R.id.log_out_tv_frag_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.favs_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new TheFavoritesFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.contact_us_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new ContactUsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.about_us_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new AboutAppFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.review_us_tv_frag_more: {
                break;
            }
            case R.id.notificat_settings_tv_frag_more: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new NotificatSettingsFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            }
            case R.id.log_out_tv_frag_more: {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                SharedPreferenceManager.removeUserLoginDetails(getActivity());
                SharedPreferenceManager.removeUserDeviceToken(getActivity());
            }
            break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
