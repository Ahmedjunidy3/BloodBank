package com.example.bloodbank.view.fragment.homeCycle.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NotificationAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.generalSource.GeneralSourcePagination;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.activity.HomeActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    @BindView(R.id.fragment_notifications_arrow_back_iv)
    ImageView fragmentNotificationsArrowBackIv;
    @BindView(R.id.fragment_notifications_toolbar)
    Toolbar fragmentNotificationsToolbar;
    @BindView(R.id.fragment_notifications_rv)
    RecyclerView fragmentNotificationsRv;
    private ApiService apiService;
    private Unbinder unbinder;

    public NotificationsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = RetrofitClient.getClient();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        setSupportToolbarAsActionBar();
        getAllNotifications();
        return view;
    }

    private void setSupportToolbarAsActionBar() {
        ((AppCompatActivity) requireActivity())
                .setSupportActionBar(fragmentNotificationsToolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("");
    }

    private void getAllNotifications() {
        Call<GeneralSourcePagination> allNotificationsCall =
                apiService.getNotifications(SharedPreferenceManager.loadData(getActivity(), "API_TOKEN"));
        allNotificationsCall.enqueue(new Callback<GeneralSourcePagination>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSourcePagination> call, @NonNull Response<GeneralSourcePagination> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        List<GeneralSourceData> notificationDataList = response.body().getData().getData();
                        NotificationAdapter notificationsAdapter = new NotificationAdapter(getActivity()
                                , notificationDataList);
                        fragmentNotificationsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        fragmentNotificationsRv.setAdapter(notificationsAdapter);
//                        Toast.makeText(getActivity(), "Notification List Size: " + notificationDataList.size()
//                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Bad Response: " + response.body().getMsg()
                                , Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.d("TAG", "Caught Error: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GeneralSourcePagination> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Failure Msg: " + t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.fragment_notifications_arrow_back_iv)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
