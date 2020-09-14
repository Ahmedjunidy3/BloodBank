package com.example.bloodbank.view.fragment.homeCycle.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.repository.PostsRepository;
import com.example.bloodbank.utility.MyGlide;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.activity.HomeActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PostDetailsFragment extends Fragment implements PostsRepository.OnSelectPostListener {


    @BindView(R.id.post_iv_frag_post_details)
    ImageView postIvFragPostDetails;
    @BindView(R.id.title_frag_post_details)
    TextView titleFragPostDetails;
    @BindView(R.id.content_frag_post_details)
    TextView contentFragPostDetails;
    private int selectedPostId;
    private Unbinder unbinder;

    public PostDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedPostId = getArguments().getInt("SELECTED_POST_ID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        getPostDetails();
        return view;
    }

    private void getPostDetails() {
        PostsRepository.getInstance().onSelectPostListener = this;
        PostsRepository.getInstance().getSelectedPostDetails(getActivity()
                , SharedPreferenceManager.loadUserApiToken(getActivity())
                , selectedPostId);
    }

    @OnClick(R.id.arrow_back_iv_frag_post_details)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void getPostDetails(GeneralSourceData postData) {
        titleFragPostDetails.setText(postData.getTitle());
        MyGlide.loadImage(getActivity(), postData.getThumbnailFullPath(), postIvFragPostDetails);
        contentFragPostDetails.setText(postData.getContent());
        if (postData.getFavourite()) {
            titleFragPostDetails.setCompoundDrawablesWithIntrinsicBounds((requireActivity())
                    .getResources().getDrawable(R.drawable.ic_favorite), null, null, null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
