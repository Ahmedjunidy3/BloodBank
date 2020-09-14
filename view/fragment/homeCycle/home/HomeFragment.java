package com.example.bloodbank.view.fragment.homeCycle.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {


    @BindView(R.id.fragment_home_tabs_layout)
    TabLayout fragmentHomeTabsLayout;
    @BindView(R.id.fragment_home_view_pager)
    ViewPager fragmentHomeViewPager;
    private Unbinder unbinder;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentHomeTabsLayout.setupWithViewPager(fragmentHomeViewPager);
        ViewPagerWithFragmentAdapter pagerAdapter =
                new ViewPagerWithFragmentAdapter(getChildFragmentManager(),
                        0, getActivity());
        fragmentHomeViewPager.setAdapter(pagerAdapter);
       return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //call onActivity Create method

//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        setUpViewpager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//    }
//
//    private void setUpViewpager(ViewPager viewPager) {
//        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
//
//        adapter.addFragment(new ArticlesFragment(),"Articles");
//        adapter.addFragment(new DonationRequestsFragment(),"Donation Requsets");
//
//        viewPager.setAdapter(adapter);
//
//    }
}
