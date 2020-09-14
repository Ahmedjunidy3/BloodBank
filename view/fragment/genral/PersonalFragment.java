package com.example.bloodbank.view.fragment.genral;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.bloodbank.data.model.login.Login;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.utility.GeneralRequest.getSpinnerData;

public class PersonalFragment extends Fragment {

    private ApiService apiService;
    private String govern;
    private GeneralSourceData cityObject;
    private List<GeneralSourceData> filteredCityList = new ArrayList<>();
    @BindView(R.id.fragment_my_profile_et_name)
    EditText fragmentMyProfileEtName;
    @BindView(R.id.fragment_my_profile_et_email)
    EditText fragmentMyProfileEtEmail;
    @BindView(R.id.fragment_my_profile_et_date_birth)
    EditText fragmentMyProfileEtDateBirth;
    @BindView(R.id.fragment_my_profile_spin_blood_type)
    Spinner fragmentMyProfileSpinBloodType;
    @BindView(R.id.fragment_my_profile_et_donation_date)
    EditText fragmentMyProfileEtDonationDate;
    @BindView(R.id.fragment_my_profile_spin_governorate)
    Spinner fragmentMyProfileSpinGovernorate;
    @BindView(R.id.fragment_my_profile_spin_city)
    Spinner fragmentMyProfileSpinCity;
    @BindView(R.id.fragment_my_profile_et_phone_no)
    EditText fragmentMyProfileEtPhoneNo;
    @BindView(R.id.fragment_my_profile_et_password)
    EditText fragmentMyProfileEtPassword;
    @BindView(R.id.fragment_my_profile_et_password_confirm)
    EditText fragmentMyProfileEtPasswordConfirm;
    @BindView(R.id.fragment_my_profile_update_btn)
    Button fragmentMyProfileUpdateBtn;
    private Unbinder unbinder;


    public PersonalFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = RetrofitClient.getClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder=ButterKnife.bind(this, view);

   //     getProfile(call, profileFragmentEtNamer, profileFragmentEtEmail, profileFragmentEtBrithday, CitiesAdpter, profileFragmentEtPhone, lastDonationDate, profileFragmentEtPassword, profileFragmentEtRePassword, bloodTypeAdaptter);

        createBloodTypeSpinner();
        createGovernSpinner();
//        createCitySpinner();
        return view;
    }

  //  private void getProfile( EditText profileFragmentEtNamer, EditText profileFragmentEtEmail, EditText profileFragmentEtBrithday, EmptySpinnerAdapter citiesAdpter, EditText profileFragmentEtPhone, DateTxt lastDonationDate, EditText profileFragmentEtPassword, EditText profileFragmentEtRePassword, EmptySpinnerAdapter bloodTypeAdaptter) {
   //     Call<Profile> call =  getClient().getProfile("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",profileFragmentEtNamer,profileFragmentEtEmail,profileFragmentEtBrithday,CitiesAdpter,profileFragmentEtPhone,lastDonationDate,profileFragmentEtPassword,profileFragmentEtRePassword,bloodTypeAdaptter);
    //    startCall(call,profileFragmentEtNamer,profileFragmentEtEmail,profileFragmentEtBrithday,CitiesAdpter,profileFragmentEtPhone,lastDonationDate,profileFragmentEtPassword,profileFragmentEtRePassword,bloodTypeAdaptter);

//    }

    private void createBloodTypeSpinner() {
        Call<GeneralSource> call = apiService.getBloodTypes();
        call.enqueue(new Callback<GeneralSource>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSource> call, @NonNull Response<GeneralSource> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        ArrayList<GeneralSourceData> bloodTypeList = new ArrayList<>();
                        GeneralAdapter bloodTypeAdapter =
                                new GeneralAdapter(requireActivity()
                                        , bloodTypeList);
                        fragmentMyProfileSpinBloodType.setAdapter(bloodTypeAdapter);
//                    Toast.makeText(getActivity(), "BloodType Ex: " + bloodTypesList.size(), Toast.LENGTH_SHORT)
//                            .show();
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
                        fragmentMyProfileSpinGovernorate.setAdapter(governAdapter);
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

//    private void startCall(Call<Profile> call, String profileFragmentEtNamer, String profileFragmentEtEmail, String profileFragmentEtBrithday, String citiesAdpter, String profileFragmentEtPhone, String lastDonationDate, String profileFragmentEtPassword, String profileFragmentEtRePassword, String bloodTypeAdaptter) {
//        call.enqueue(new Callback<Profile>() {
//            @Override
//            public void onResponse(Call<Profile> call, Response<Profile> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//                        setData((Client) getClient());
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Profile> call, Throwable t) {
//
//            }
//        });
//    }

//    @OnClick(R.id.profile_fragment_btn_Done)
//    public void onViewClicked() {
//     //   startCall(call, profileFragmentEtNamer, profileFragmentEtEmail, profileFragmentEtBrithday, CitiesAdpter, profileFragmentEtPhone, lastDonationDate, profileFragmentEtPassword, profileFragmentEtRePassword, bloodTypeAdaptter);
//
//
//    }
    @OnClick({R.id.fragment_my_profile_update_btn})
    void onViewClicked(View view) {
        String registName = fragmentMyProfileEtName.getText().toString().toLowerCase().trim();
        String registEmail = fragmentMyProfileEtEmail.getText().toString().toLowerCase().trim();
        String registDateBirth = fragmentMyProfileEtDateBirth.getText().toString().trim();
        String registBloodType = String.valueOf(fragmentMyProfileSpinBloodType.getSelectedItemPosition());
        String registLastDonateDate = fragmentMyProfileEtDonationDate.getText().toString().trim();
//        String registGovern = String.valueOf(fragmentMyProfileSpinGovernorate.getSelectedItemPosition());
        String registCity = String.valueOf(fragmentMyProfileSpinBloodType.getSelectedItemPosition());
        String registPhoneNo = fragmentMyProfileEtPhoneNo.getText().toString().trim();
        String registPass = fragmentMyProfileEtPassword.getText().toString().trim();
        String registPassConfirm = fragmentMyProfileEtPasswordConfirm.getText().toString().trim();
        if (view.getId() == R.id.fragment_my_profile_update_btn) {
            if (registName.equals("") | registEmail.equals("") | registDateBirth.equals("") | registLastDonateDate.equals("") |
                    registPhoneNo.equals("") | registPass.equals("") | registPassConfirm.equals("")) {
                Toast.makeText(getActivity(), "Please fill all the blank cells", Toast.LENGTH_LONG).show();
            }

            if (!registPass.equals(registPassConfirm)) {
                Toast.makeText(getActivity(), "Your passwords are not identical", Toast.LENGTH_LONG).show();
            }
            if (!registName.equals("") && !registEmail.equals("") && !registDateBirth.equals("") && !registLastDonateDate.equals("") &&
                    !registPhoneNo.equals("") && !registPass.equals("") && !registPassConfirm.equals("")
                    && (registPass.equals(registPassConfirm))) {
                Call<Login> call = apiService
                        .updateProfile("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl");
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        assert response.body() != null;
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getActivity(), response.body().getMsg()
                                    , Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                        Toast.makeText(getActivity(), "Failure Msg: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
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


//    private void setData(Client client){
//        bloodtypeselectedId = bloodTypeAdaptter.selectedId;
//        governmaentselectID = goviermentAdaptter.selectedId;
//        cityselectedId = CitiesAdpter.selectedId;
//
//
//
//       profileFragmentEtEmail.setText(client.getName());
//        profileFragmentEtBrithday.setText(client.getBirthDate());
//        profileFragmentEtNamer.setText(client.getName());
//        profileFragmentEtPhone.setText(client.getPhone());
//        profileFragmentEtPassword.setText("");
//        profileFragmentEtRePassword.setText("");
//   }
}
