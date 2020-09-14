package com.example.bloodbank.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.homeCycle.post.PostDetailsFragment;


public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.activity_article_details_container);
        if (fragment == null) {
            fragment = new PostDetailsFragment();
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_article_details_container, fragment)
                    .commit();
        }




    }
}
