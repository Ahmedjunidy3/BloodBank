package com.example.bloodbank.data.repository;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.example.bloodbank.data.model.generalSource.GeneralSource2;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.generalSource.GeneralSourcePagination;
import com.example.bloodbank.utility.local.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;

public class PostsRepository {

    public OnGetPostsListener onGetPostsListener;
    public OnGetFilteredPostsListener onGetFilteredPostsListener;
    public OnGetFavPostsListener onGetFavPostsListener;
    public OnSelectPostListener onSelectPostListener;
    private static PostsRepository postsRepo;

    public static PostsRepository getInstance() {
        if (postsRepo == null) {
            postsRepo = new PostsRepository();
        }
        return postsRepo;
    }

    public void getPostsForUser(Activity activity) {
        getClient().getPosts(SharedPreferenceManager.loadUserApiToken(activity))
                .enqueue(new Callback<GeneralSourcePagination>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSourcePagination> call, @NonNull Response<GeneralSourcePagination> response) {
                        try {
                            GeneralSourcePagination postsResponse = response.body();
                            if (postsResponse != null) {
                                if (postsResponse.getStatus() == 1) {
                                    onGetPostsListener.showPosts(postsResponse.getData().getData());
                                } else {
                                    Toast.makeText(activity, postsResponse.getMsg()
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSourcePagination> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Failure Error: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void filterPostsForUser(Activity activity, String keyword) {
        getClient().filterPosts(SharedPreferenceManager.loadUserApiToken(activity), keyword)
                .enqueue(new Callback<GeneralSourcePagination>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSourcePagination> call, @NonNull Response<GeneralSourcePagination> response) {
                        try {
                            GeneralSourcePagination postsResponse = response.body();
                            if (postsResponse != null) {
                                if (postsResponse.getStatus() == 1) {
                                    onGetFilteredPostsListener.showFilteredPosts(postsResponse.getData().getData());
                                } else {
                                    Toast.makeText(activity, postsResponse.getMsg()
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSourcePagination> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Failure Error: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getFavPostsForUser(Activity activity, String apiToken) {
        getClient().getFavPosts(apiToken)
                .enqueue(new Callback<GeneralSourcePagination>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSourcePagination> call, @NonNull Response<GeneralSourcePagination> response) {
                        try {
                            GeneralSourcePagination FavPostsResponse = response.body();
                            if (FavPostsResponse != null) {
                                if (FavPostsResponse.getStatus() == 1) {
                                    onGetFavPostsListener.showFavPosts(FavPostsResponse.getData().getData());
                                } else {
                                    Toast.makeText(activity, FavPostsResponse.getMsg()
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSourcePagination> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Failure Error: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addOrRemoveFavPost(Activity activity, String postId, String apiToken) {
        getClient().addOrRemoveFavPost(postId, apiToken)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            GeneralSource2 addOrRemoveFavPostResponse = response.body();
                            if (addOrRemoveFavPostResponse != null) {
                                if (addOrRemoveFavPostResponse.getStatus() == 1) {
                                    Toast.makeText(activity, addOrRemoveFavPostResponse.getMsg()
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Failure Error: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getSelectedPostDetails(Activity activity, String apiToken, Integer postId) {
        getClient().getPostDetails(apiToken, postId)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            GeneralSource2 postDataResponse = response.body();
                            if (postDataResponse != null) {
                                if (postDataResponse.getStatus() == 1) {
                                    onSelectPostListener
                                            .getPostDetails(postDataResponse.getGeneralSource2Data());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Failure Error: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public interface OnGetPostsListener {
        void showPosts(List<GeneralSourceData> postsList);
    }

    public interface OnGetFilteredPostsListener {
        void showFilteredPosts(List<GeneralSourceData> postsList);
    }

    public interface OnGetFavPostsListener {
        void showFavPosts(List<GeneralSourceData> postsList);
    }

    public interface OnSelectPostListener {
        void getPostDetails(GeneralSourceData postData);
    }

}
