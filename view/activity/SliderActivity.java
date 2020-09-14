//package com.example.bloodbank.view.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//import com.example.bloodbank.R;
//import com.example.bloodbank.adapter.SliderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//public class SliderActivity extends AppCompatActivity {


//    SliderAdapter sliderAdapter;
//
//    Animation animation;
//    @BindView(R.id.viewPager_slider_sa)
//    ViewPager viewPagerSliderSa;
//    @BindView(R.id.next1)
//    ImageView next1;
//    @BindView(R.id.next2)
//    ImageView next2;
//    @BindView(R.id.next3)
//    ImageView next3;
//    @BindView(R.id.slider_btn_start)
//    ImageButton sliderBtnStart;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_slider);
//        ButterKnife.bind(this);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//        //call adapter
//        sliderAdapter = new SliderAdapter(this);
//        viewPagerSliderSa.setAdapter(sliderAdapter);
//
//        viewPagerSliderSa.addOnPageChangeListener(changeListener);
//    }
//
//    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            if (position == 0) {
//                sliderBtnStart.setVisibility(View.INVISIBLE);
//            } else {
//                animation = AnimationUtils.loadAnimation(SliderActivity.this, R.anim.slider_btn_start);
//                sliderBtnStart.setAnimation(animation);
//                sliderBtnStart.setVisibility(View.VISIBLE);
//            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
//
//    @OnClick(R.id.slider_btn_start)
//    public void onViewClicked() {
//        startActivity(new Intent(this,UserActivity.class));
//    }
//}
