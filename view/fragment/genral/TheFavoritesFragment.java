package com.example.bloodbank.view.fragment.genral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostsAdapter;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.repository.PostsRepository;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.fragment.homeCycle.home.MoreFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TheFavoritesFragment extends Fragment implements PostsRepository.OnGetFavPostsListener {

    @BindView(R.id.toolbar_frag_the_favorites)
    Toolbar toolbarFragTheFavorites;
    @BindView(R.id.rv_frag_the_favorites)
    RecyclerView rvFragTheFavorites;
    private Unbinder unbinder;

    public TheFavoritesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_the_favorites, container, false);
        unbinder = ButterKnife.bind(this, view);
        setToolbarAsActionBar();
        getMyFavPosts();
        return view;
    }

    private void getMyFavPosts() {
        PostsRepository.getInstance().onGetFavPostsListener = this;
        PostsRepository.getInstance().getFavPostsForUser(getActivity()
                , SharedPreferenceManager.loadUserApiToken(getActivity()));
    }


    @OnClick(R.id.frag_the_favorites_arrow_back_iv)
    public void onViewClicked() {
        Fragment fragment = requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.container_activity_home);
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_home, new MoreFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setToolbarAsActionBar() {
        ((AppCompatActivity) requireActivity())
                .setSupportActionBar(toolbarFragTheFavorites);
        Objects.requireNonNull(((AppCompatActivity) requireActivity())
                .getSupportActionBar()).setTitle("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showFavPosts(List<GeneralSourceData> postsList) {
        ArrayList<GeneralSourceData> favPostsArrayList = new ArrayList<>(postsList);
        rvFragTheFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        PostsAdapter favAdapter = new PostsAdapter(getActivity(), favPostsArrayList);
        rvFragTheFavorites.setAdapter(favAdapter);
    }
}
