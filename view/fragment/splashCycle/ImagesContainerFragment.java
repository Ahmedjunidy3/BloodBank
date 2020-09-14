package com.example.bloodbank.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ImagesPagerAdapter;
import com.example.bloodbank.view.activity.UserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImagesContainerFragment extends Fragment {

    @BindView(R.id.vp_frag_images_container)
    CustomViewPager VpFragImagesContainer;
    private Unbinder unbinder;

    public ImagesContainerFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images_container
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        ImagesPagerAdapter pagerAdapter =
                new ImagesPagerAdapter(getChildFragmentManager(), 0);
        VpFragImagesContainer.setAdapter(pagerAdapter);
        setVPListener();
        return view;
    }

    private void setVPListener() {
        VpFragImagesContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    VpFragImagesContainer.setScrollablePagerStatus(false);
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(getActivity(), UserActivity.class);
                        startActivity(intent);
                    }, 3000);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
