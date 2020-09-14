package com.example.bloodbank.view.fragment.homeCycle.post;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostsAdapter;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.data.repository.PostsRepository;
import com.example.bloodbank.utility.HelperMethod;
import com.example.bloodbank.utility.OnEndLess;
import com.example.bloodbank.view.fragment.homeCycle.donation.DonationReqsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;


public class PostsFragment extends Fragment implements PostsRepository.OnGetPostsListener, PostsRepository.OnGetFilteredPostsListener {



    @BindView(R.id.search_et_frag_posts)
    EditText searchEtFragPosts;
    @BindView(R.id.rv_frag_posts)
    RecyclerView rvFragPosts;
    private Unbinder unbinder;
    private ArrayList<GeneralSourceData> filteredPosts = new ArrayList<>();
    private PostsAdapter postsAdapter;


    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HelperMethod.changeLang(getActivity(),"ar");

        View view =  inflater.inflate(R.layout.fragment_articles, container, false);
        unbinder = ButterKnife.bind(this, view);

        getPosts();
        onSearchListener();

        return view;
    }

    private void onSearchListener() {
        PostsRepository.getInstance().onGetFilteredPostsListener = this;
        searchEtFragPosts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PostsRepository.getInstance().filterPostsForUser(getActivity()
                        , searchEtFragPosts.getText().toString().trim());
                postsAdapter = new PostsAdapter(getActivity(), filteredPosts);
                rvFragPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvFragPosts.setAdapter(postsAdapter);
                if (count == 0) {
                    getPosts();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getPosts() {
        PostsRepository.getInstance().onGetPostsListener = this;
        PostsRepository.getInstance().getPostsForUser(getActivity());
    }

    @OnClick({R.id.floating_btn_frag_posts})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.floating_btn_frag_posts) {
            Fragment fragment = requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.container_activity_home);
            if (fragment != null) {
                Fragment donationReqFormFrag = new DonationReqsFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_activity_home, donationReqFormFrag)
                        .commit();
            }
        }
    }

    @Override
    public void showPosts(List<GeneralSourceData> postsList) {
        ArrayList<GeneralSourceData> posts = new ArrayList<>(postsList);
        rvFragPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        postsAdapter = new PostsAdapter(getActivity(), posts);
        rvFragPosts.setAdapter(postsAdapter);
    }

    @Override
    public void showFilteredPosts(List<GeneralSourceData> postsList) {
        filteredPosts = new ArrayList<>(postsList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
