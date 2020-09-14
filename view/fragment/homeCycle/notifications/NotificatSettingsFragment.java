package com.example.bloodbank.view.fragment.homeCycle.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NotificationSettingAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.generalSource.GeneralSource;
import com.example.bloodbank.data.model.generalSource.GeneralSource2;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.fragment.homeCycle.home.HomeFragment;

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

public class NotificatSettingsFragment extends Fragment {

    @BindView(R.id.fragment_notificat_settings_toolbar)
    Toolbar fragmentNotificatSettingsToolbar;
    @BindView(R.id.fragment_notificat_settings_arrow_back_iv)
    ImageView fragmentNotificatSettingsArrowBackIv;
    @BindView(R.id.fragment_notificat_settings_rv_blood_types)
    RecyclerView fragmentNotificatSettingsRvBloodTypes;
    @BindView(R.id.fragment_notificat_settings_rv_governs)
    RecyclerView fragmentNotificatSettingsRvGoverns;
    @BindView(R.id.fragment_notificat_settings_btn_save)
    Button fragmentNotificatSettingsBtnSave;
    private ApiService apiService;
    private List<String> governorates = new ArrayList<>();
    private List<String> bloodTypes = new ArrayList<>();
    private static final String TAG = "NotificatSettings";
    private NotificationSettingAdapter bloodTypeAdapter;
    private NotificationSettingAdapter governAdapter;
    private Unbinder unbinder;

    public NotificatSettingsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = RetrofitClient.getClient();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_setting
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        setToolbarAsActionBar();
        getNotificationSettings();
        getBloodTypes();
        getGovernorates();
        return view;
    }

    private void getNotificationSettings() {
        apiService.getNotificationSettings(SharedPreferenceManager
                .loadData(getActivity(), "API_TOKEN")).enqueue(new Callback<GeneralSource2>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        governorates = response.body().getGeneralSource2Data().getGovernorates();
                        bloodTypes = response.body().getGeneralSource2Data().getBloodTypes();
                    } else {
                        Toast.makeText(getActivity(), "Wrong Response: " + response.body().getMsg()
                                , Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.d(TAG, "Response Caught Error: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
                try {
                    Toast.makeText(getActivity(), "Failure Msg: " + t.getMessage()
                            , Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.d(TAG, "Failure Caught Error: " + e.getMessage());

                }
            }
        });
    }

    private void setToolbarAsActionBar() {
        ((AppCompatActivity) requireActivity())
                .setSupportActionBar(fragmentNotificatSettingsToolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity())
                .getSupportActionBar()).setTitle("");
    }

    private void getBloodTypes() {
        Call<GeneralSource> call = apiService.getBloodTypes();
        call.enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        List<GeneralSourceData> bloodTypeList = response.body().getData();
                        bloodTypeAdapter = new NotificationSettingAdapter(getActivity()
                                , bloodTypeList, bloodTypes);
                        fragmentNotificatSettingsRvBloodTypes
                                .setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        fragmentNotificatSettingsRvBloodTypes.setAdapter(bloodTypeAdapter);
                    }
                } catch (Exception e) {
                    Log.d("TAG", "Caught Error: " + e.getMessage());
                }

            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Failure Error: " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    private void getGovernorates() {
        Call<GeneralSource> call = apiService.getGovernates();
        call.enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        List<GeneralSourceData> governList = response.body().getData();
                        governAdapter = new NotificationSettingAdapter(getActivity(), governList
                                , governorates);
                        fragmentNotificatSettingsRvGoverns
                                .setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        fragmentNotificatSettingsRvGoverns.setAdapter(governAdapter);
                    }
                } catch (Exception e) {
                    Log.d("TAG", "Response Error: " + e.getMessage());
                }

            }

            @Override
            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @OnClick({R.id.fragment_notificat_settings_arrow_back_iv, R.id.fragment_notificat_settings_btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_notificat_settings_arrow_back_iv: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    Fragment homeFragment = new HomeFragment();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, homeFragment)
                            .commit();
                }
                break;
            }
            case R.id.fragment_notificat_settings_btn_save: {
                apiService.setNotificatSettings(SharedPreferenceManager.loadData(getActivity()
                        , "API_TOKEN"), governAdapter.getNewIds(), bloodTypeAdapter.getNewIds())
                        .enqueue(new Callback<GeneralSource2>() {
                            @Override
                            public void onResponse(@NonNull Call<GeneralSource2> call, @NonNull Response<GeneralSource2> response) {
                                try {
                                    assert response.body() != null;
                                    if (response.body().getStatus() == 1) {
                                        Toast.makeText(getActivity(), response.body().getMsg()
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<GeneralSource2> call, @NonNull Throwable t) {
                                try {
                                    Toast.makeText(getActivity(), "Failure Msg: " + t.getMessage()
                                            , Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
