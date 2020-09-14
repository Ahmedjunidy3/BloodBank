package com.example.bloodbank.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;


import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.generalSource.GeneralSourcePagination;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;


public class DonationsRepository {

    private static final String TAG = "DonationsRepository";
    private static DonationsRepository donationsRepo;
   public OnGetDonationReqsListener onGetDonationReqsListener;

    public static DonationsRepository getInstance() {
        if (donationsRepo == null) {
            donationsRepo = new DonationsRepository();
        }
        return donationsRepo;
    }

    public void getAllDonationReqs(String apiToken) {
        getClient().getDonationReqs(apiToken)
                .enqueue(new Callback<GeneralSourcePagination>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSourcePagination> call, @NonNull Response<GeneralSourcePagination> response) {
                        GeneralSourcePagination donationReqsResponse = response.body();
                        if (donationReqsResponse != null && donationReqsResponse.getStatus() == 1) {
                            onGetDonationReqsListener.showDonationReqs(donationReqsResponse.getData().getData());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSourcePagination> call, @NonNull Throwable t) {
                        Log.d(TAG, Objects.requireNonNull(t.getMessage()));
                    }
                });
    }

    public void FilterAllDonationReqs(String apiToken, String bloodTypeId, String cityId) {
        getClient().filterDonationReqs(apiToken, bloodTypeId, cityId)
                .enqueue(new Callback<GeneralSourcePagination>() {
                    @Override
                    public void onResponse(@NonNull Call<GeneralSourcePagination> call, @NonNull Response<GeneralSourcePagination> response) {
                        GeneralSourcePagination filteredDonationsResponse = response.body();
                        if (filteredDonationsResponse != null && filteredDonationsResponse.getStatus() == 1) {
                            onGetDonationReqsListener
                                    .showFilteredDonationReq(filteredDonationsResponse.getData().getData());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GeneralSourcePagination> call, @NonNull Throwable t) {
                        Log.d(TAG, Objects.requireNonNull(t.getMessage()));
                    }
                });
    }

    public interface OnGetDonationReqsListener {
        void showDonationReqs(List<GeneralSourceData> donationReqList);

        void showFilteredDonationReq(List<GeneralSourceData> filteredDonationReqList);
    }

}
