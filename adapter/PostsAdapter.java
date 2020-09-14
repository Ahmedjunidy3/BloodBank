package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.posts.PostsData;
import com.example.bloodbank.data.repository.PostsRepository;
import com.example.bloodbank.utility.MyGlide;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.activity.PostDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ArticlesViewHolder> {

    private final Context context;
    private final ArrayList<GeneralSourceData> postsList;

    public PostsAdapter(Context context, ArrayList<GeneralSourceData> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recyc_view_post, parent, false);
        ButterKnife.bind(this, view);
        return new ArticlesViewHolder(view, context, postsList);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        holder.setData(position);
        holder.setAction(position);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    static class ArticlesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_iv_item_recyc_view_post)
        ImageView postIvItemRecycViewPost;
        @BindView(R.id.unfav_iv_item_recyc_view_post)
        ImageView unfavIvItemRecycViewPost;
        @BindView(R.id.title_tv_item_recyc_view_post)
        TextView titleTvItemRecycViewPost;
        private final Context context;
        private final ArrayList<GeneralSourceData> postsList;
        private final String apiToken;

        ArticlesViewHolder(@NonNull View itemView, Context context
                , ArrayList<GeneralSourceData> postsList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.postsList = postsList;
            unfavIvItemRecycViewPost.setTag(R.drawable.iv_unfav_post);
            apiToken = SharedPreferenceManager.loadUserApiToken((Activity) context);
        }

        private void setData(int position) {
            GeneralSourceData post = postsList.get(position);
            MyGlide.loadImage(context, post.getThumbnailFullPath(), postIvItemRecycViewPost);
            titleTvItemRecycViewPost.setText(post.getCategory().getName());
            markAllFavPosts(post);
        }

        void setAction(int position) {
            int selectedPostId = postsList.get(position).getId();
            postIvItemRecycViewPost.setOnClickListener(v -> {
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra("SELECTED_POST_ID", selectedPostId);
                context.startActivity(intent);
            });

            unfavIvItemRecycViewPost.setOnClickListener(v -> {
                PostsRepository.getInstance().addOrRemoveFavPost((Activity) context
                        , String.valueOf(selectedPostId), apiToken);
                addOrRemoveFavPostMark();
            });
        }

        private void markAllFavPosts(GeneralSourceData post) {
            if (post.getFavourite()) {
                addOrRemoveFavPostMark();
            }
        }

        private void addOrRemoveFavPostMark() {
            if (unfavIvItemRecycViewPost.getTag().equals(R.drawable.iv_unfav_post)) {
                unfavIvItemRecycViewPost.setImageResource(R.drawable.iv_fav_post);
                unfavIvItemRecycViewPost.setTag(R.drawable.iv_fav_post);
            } else {
                unfavIvItemRecycViewPost.setImageResource(R.drawable.iv_unfav_post);
                unfavIvItemRecycViewPost.setTag(R.drawable.iv_unfav_post);
            }
        }
    }


}

