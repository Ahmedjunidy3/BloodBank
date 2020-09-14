package com.example.bloodbank.data.repository;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.example.bloodbank.data.model.generalSource.GeneralSource;
import com.example.bloodbank.data.model.generalSource.GeneralSource2;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;


public class GeneralRepository {

    private static GeneralRepository generalRepos;
    public OnGetBloodTypeSpinListListener onGetBloodTypeSpinListListener;
    public OnGetGovernSpinListListener onGetGovernSpinListListener;
    public OnGetCitySpinListListener onGetCitySpinListListener;

    public static GeneralRepository getInstance() {
        if (generalRepos == null) {
            generalRepos = new GeneralRepository();
        }
        return generalRepos;
    }



    public void getBloodTypesList(Activity activity) {
        getClient().getBloodTypes().enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    GeneralSource governsResponse = response.body();
                    if (governsResponse != null && governsResponse.getStatus() == 1) {
                        onGetBloodTypeSpinListListener
                                .showBloodTypeList(Objects.requireNonNull(response.body()).getData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    public void getGovernsList(Activity activity) {
        getClient().getGovernates().enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    GeneralSource governsResponse = response.body();
                    if (governsResponse != null && governsResponse.getStatus() == 1) {
                        onGetGovernSpinListListener
                                .showGovernList(Objects.requireNonNull(response.body()).getData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    public void getCitiesList(Activity activity, String governId) {
        getClient().getCities(governId).enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    GeneralSource citiesResponse = response.body();
                    if (citiesResponse != null && citiesResponse.getStatus() == 1) {
                        onGetCitySpinListListener
                                .showCityList(Objects.requireNonNull(response.body()).getData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    public void sendMyMessage(Activity activity, String apiToken, String title, String message) {
        getClient().contactManagement(apiToken, title, message)
                .enqueue(new Callback<GeneralSource2>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                        try {
                            GeneralSource2 userMsgResponse = response.body();
                            if (userMsgResponse != null && userMsgResponse.getStatus() == 1) {
                                Toast.makeText(activity, userMsgResponse.getMsg()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_LONG)
                                .show();
                    }
                });

    }

    public interface OnGetBloodTypeSpinListListener {
        void showBloodTypeList(List<GeneralSourceData> bloodTypesList);
    }

    public interface OnGetGovernSpinListListener {
        void showGovernList(List<GeneralSourceData> GovernsList);
    }

    public interface OnGetCitySpinListListener {
        void showCityList(List<GeneralSourceData> CitiesList);
    }


}
