package com.example.bloodbank.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.EmptySpinnerAdapter;
import com.example.bloodbank.adapter.GeneralAdapter;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.repository.GeneralRepository;
import com.example.bloodbank.data.repository.UserRepository;
import com.example.bloodbank.utility.DateTxt;
import com.example.bloodbank.data.model.register.Register;
import com.example.bloodbank.view.fragment.BaseFragment;

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

import static com.example.bloodbank.utility.HelperMethod.replaceFragment;
import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.utility.GeneralRequest.getSpinnerData;
import static com.example.bloodbank.utility.network.InternetState.isConnected;


public class RegisterFragment extends Fragment implements GeneralRepository.OnGetBloodTypeSpinListListener
        , GeneralRepository.OnGetGovernSpinListListener, GeneralRepository.OnGetCitySpinListListener {


    @BindView(R.id.activity_register_spin_blood_type)
    Spinner activityRegisterSpinBloodType;
    @BindView(R.id.activity_register_spin_govern)
    Spinner activityRegisterSpinGovern;
    @BindView(R.id.activity_register_spin_city)
    Spinner activityRegisterSpinCity;
    @BindView(R.id.activity_register_et_name)
    EditText activityRegisterEtName;
    @BindView(R.id.activity_register_et_email)
    EditText activityRegisterEtEmail;
    @BindView(R.id.activity_register_et_date_birth)
    EditText activityRegisterEtDateBirth;
    @BindView(R.id.activity_register_et_donation_date)
    EditText activityRegisterEtDonationDate;
    @BindView(R.id.activity_register_et_phone_no)
    EditText activityRegisterEtPhoneNo;
    @BindView(R.id.activity_register_et_password)
    EditText activityRegisterEtPassword;
    @BindView(R.id.activity_register_et_password_confirm)
    EditText activityRegisterEtPasswordConfirm;
    @BindView(R.id.activity_register_btn_register)
    Button activityRegisterBtnRegister;
    private final GeneralRepository generalRepo = new GeneralRepository();
    private Unbinder unbinder;
    private int governId = 0;
    private int selectedCityPos;
    private ArrayList<GeneralSourceData> citiesArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder= ButterKnife.bind(this, view);


        createBloodTypeSpinner();
        createGovernSpinner();
        createCitySpinner();

//        bloodTypeAdaptter = new EmptySpinnerAdapter(getActivity());
//        getSpinnerData(getActivity(),   registerFragmentSpinnerBloodtype ,   bloodTypeAdaptter , getString(R.string.Select_Blood_types) ,
//        getClient().getBloodTypes());
//
//        goviermentAdaptter = new EmptySpinnerAdapter(getActivity());
//        CitiesAdpter = new EmptySpinnerAdapter(getActivity());
//        getSpinnerData(getActivity(),   registerFragmentSpinnerGovernment ,   goviermentAdaptter , getString(R.string.Select_governments) ,
//                getClient().getgovernorate(),registerFragmentSpinnerCity,CitiesAdpter , getString(R.string.Select_Cities),
//                getClient().getCities(goviermentAdaptter.selectedId));
        return view;
    }

    @OnClick({R.id.activity_register_btn_register})
    void onViewClicked(View view) {
        if (view.getId() == R.id.activity_register_btn_register) {
            UserRepository.setMySignUpDetails(getActivity()
                    , activityRegisterEtName.getText().toString().trim()
                    , activityRegisterEtEmail.getText().toString().trim()
                    , activityRegisterEtDateBirth.getText().toString().trim()
                    , String.valueOf(citiesArrayList.get(selectedCityPos).getId())
                    , activityRegisterEtPhoneNo.getText().toString().trim()
                    , activityRegisterEtDonationDate.getText().toString().trim()
                    , activityRegisterEtPassword.getText().toString().trim()
                    , activityRegisterEtPasswordConfirm.getText().toString().trim()
                    , String.valueOf(((activityRegisterSpinBloodType.getSelectedItemPosition())))
            );
        }
    }
    private void createBloodTypeSpinner() {
    generalRepo.onGetBloodTypeSpinListListener = this;
        generalRepo.getBloodTypesList(getActivity());
}

    private void createGovernSpinner() {
        generalRepo.onGetGovernSpinListListener = this;
        generalRepo.getGovernsList(getActivity());
        activityRegisterSpinGovern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                governId = position;
                createCitySpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createCitySpinner() {
        generalRepo.onGetCitySpinListListener = this;
        generalRepo.getCitiesList(getActivity(), String.valueOf(governId));
        activityRegisterSpinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showBloodTypeList(List<GeneralSourceData> bloodTypesList) {
        ArrayList<GeneralSourceData> bloodTypesArrayList = new ArrayList<>(bloodTypesList);
        GeneralSourceData dataObject = new GeneralSourceData();
        dataObject.setName(requireActivity().getString(R.string.blood_type));
        bloodTypesArrayList.add(0, dataObject);
        GeneralAdapter bloodTypesAdapter = new GeneralAdapter(getActivity(), bloodTypesArrayList);
        activityRegisterSpinBloodType.setAdapter(bloodTypesAdapter);
    }

    @Override
    public void showGovernList(List<GeneralSourceData> GovernsList) {
        ArrayList<GeneralSourceData> GovernsArrayList = new ArrayList<>(GovernsList);
        GeneralSourceData dataObject = new GeneralSourceData();
        dataObject.setName(requireActivity().getString(R.string.governorate));
        GovernsArrayList.add(0 , dataObject);
        GeneralAdapter governsAdapter = new GeneralAdapter(getActivity(), GovernsArrayList);
        activityRegisterSpinGovern.setAdapter(governsAdapter);

    }

    @Override
    public void showCityList(List<GeneralSourceData> citiesList) {
        citiesArrayList = new ArrayList<>(citiesList);
        if (governId == 0) {
            GeneralSourceData dataObject = new GeneralSourceData();
            dataObject.setName(requireActivity().getString(R.string.city));
            citiesArrayList.add(0, dataObject);
        }
        GeneralAdapter citiesAdapter = new GeneralAdapter(getActivity(), citiesArrayList);
        activityRegisterSpinCity.setAdapter(citiesAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
