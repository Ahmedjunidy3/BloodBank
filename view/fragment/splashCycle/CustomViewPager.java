package com.example.bloodbank.view.fragment.splashCycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private Boolean scrollablePagerStatus = true;

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollablePagerStatus(Boolean scrollablePagerStatus) {
        this.scrollablePagerStatus = scrollablePagerStatus;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scrollablePagerStatus && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollablePagerStatus && super.onInterceptTouchEvent(ev);
    }
}
