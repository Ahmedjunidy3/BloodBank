package com.example.bloodbank.view.fragment.homeCycle.donation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.bloodbank.R;
import com.example.bloodbank.adapter.GeneralAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.generalSource.GeneralSource;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DonationReqFormFragment extends Fragment {

    @BindView(R.id.fragment_donation_req_et_name)
    EditText fragmentDonationReqEtName;
    @BindView(R.id.fragment_donation_req_et_age)
    EditText fragmentDonationReqEtAge;
    @BindView(R.id.fragment_donation_req_spin_blood_type)
    Spinner fragmentDonationReqSpinBloodType;
    @BindView(R.id.fragment_donation_req_et_no_of_blood_bags)
    EditText fragmentDonationReqEtNoOfBloodBags;
    @BindView(R.id.fragment_donation_req_et_hospital_address)
    EditText fragmentDonationReqEtHospitalAddress;
    @BindView(R.id.fragment_donation_req_spin_govern)
    Spinner fragmentDonationReqSpinGovern;
    @BindView(R.id.fragment_donation_req_spin_city)
    Spinner fragmentDonationReqSpinCity;
    @BindView(R.id.fragment_donation_req_et_phone_no)
    EditText fragmentDonationReqEtPhoneNo;
    @BindView(R.id.fragment_donation_req_et_notes)
    EditText fragmentDonationReqEtNotes;
    @BindView(R.id.fragment_donation_req_btn_send)
    Button fragmentDonationReqBtnSend;
    private ApiService apiService;
    private Unbinder unbinder;

    public DonationReqFormFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = RetrofitClient.getClient();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_req_form, container, false);
        unbinder = ButterKnife.bind(this, view);
        onAddressEtTouchedListener();
        createBloodTypeSpinner();
        createGovernSpinner();
//        createCitySpinner();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onAddressEtTouchedListener() {
        fragmentDonationReqEtHospitalAddress.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getX() >= (fragmentDonationReqEtHospitalAddress.getTotalPaddingRight())) {
                    Fragment fragment = requireActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.container_activity_home);
                    if (fragment != null) {
                        Fragment hospitAddress = new HospitalAddressFragment();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_activity_home, hospitAddress)
                                .commit();
                    }
                }
            }
            return true;
        });
    }

    private void createBloodTypeSpinner() {
        Call<GeneralSource> call = apiService.getBloodTypes();
        call.enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        ArrayList<GeneralSourceData> bloodTypeList = new ArrayList<>(response.body().getData());
                        GeneralAdapter bloodTypeAdapter =
                                new GeneralAdapter(requireActivity()
                                        , bloodTypeList);
                        fragmentDonationReqSpinBloodType.setAdapter(bloodTypeAdapter);
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

    private void createGovernSpinner() {
        Call<GeneralSource> call = apiService.getGovernates();
        call.enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        ArrayList<GeneralSourceData> governList = new ArrayList<>(response.body().getData());
                        GeneralAdapter governAdapter = new GeneralAdapter(getActivity()
                                , governList);
                        fragmentDonationReqSpinGovern.setAdapter(governAdapter);
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

//    private void createCitySpinner() {
//        Call<GeneralSource> call = apiService.getCities();
//        call.enqueue(new Callback<GeneralSource>() {
//            @Override
//            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
//                try {
//                    assert response.body() != null;
//                    if (response.body().getStatus() == 1) {
//                        ArrayList<GeneralSourceData> cityList = new ArrayList<>(response.body().getData());
//                        GeneralAdapter cityAdapter = new GeneralAdapter(getActivity()
//                                                                , cityList);
//                        fragmentDonationReqSpinGovern
//                                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                                    @Override
//                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                        if (position == 0) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("الدقهلية")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 1) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("الغربية")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//
//                                                }
//                                            }
//                                        }
//                                        if (position == 2) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("المنوفية")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 3) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("كفر الشيخ")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 4) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("سوهاج")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 5) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("البحر الأحمر")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                } else if (i == 11) {
//                                                    cityObject = new GeneralSourceData();
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 6) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("الشرقية")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                } else if (i == 11) {
//                                                    cityObject = new GeneralSourceData();
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 7) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("الوادى الجديد")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                } else if (i == 11) {
//                                                    cityObject = new GeneralSourceData();
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 8) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("شمال سيناء")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                } else if (i == 11) {
//                                                    cityObject = new GeneralSourceData();
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 9) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("جنوب سيناء")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                } else if (i == 11) {
//                                                    cityObject = new GeneralSourceData();
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//                                            }
//                                        }
//                                        if (position == 10) {
//                                            filteredCityList.clear();
//                                            for (int i = 0; i < cityList.size(); i++) {
//                                                govern = cityList.get(i).getGovernorate().getName();
//                                                if (govern.equals("الإسماعيلية")) {
//                                                    cityObject = cityList.get(i);
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                } else if (i == 11) {
//                                                    cityObject = new GeneralSourceData();
//                                                    filteredCityList.add(cityObject);
//                                                    fragmentDonationReqSpinCity.setAdapter(cityAdapter);
//                                                }
//
//
//                                            }
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onNothingSelected(AdapterView<?> parent) {
//
//                                    }
//                                });
//
//
//                    }
//                } catch (Exception e) {
//                    Log.d("TAG", "Response Error: " + e.getMessage());
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<GeneralSource> call, @NonNull Throwable t) {
//                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
