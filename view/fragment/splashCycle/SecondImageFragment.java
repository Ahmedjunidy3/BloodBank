package com.example.bloodbank.view.fragment.splashCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SecondImageFragment extends Fragment {
    @BindView(R.id.iv_frag_second_image)
    ImageView IvFragSecondImage;
    private Unbinder unbinder;

    public SecondImageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        setImage();
        return view;
    }

    private void setImage() {
        IvFragSecondImage.setImageResource(R.drawable.img_02);
        IvFragSecondImage.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
