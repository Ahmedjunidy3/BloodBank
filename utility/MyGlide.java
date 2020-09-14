package com.example.bloodbank.utility;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MyGlide {

    public static void loadImage(Context context, String url, ImageView iv) {
       Glide.with(context)
                .load(url)
                .into(iv);
    }
}

